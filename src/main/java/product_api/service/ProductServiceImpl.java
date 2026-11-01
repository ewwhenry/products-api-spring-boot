package product_api.service;

import product_api.dto.request.CreateProductRequest;
import product_api.dto.response.ProductResponse;
import product_api.exception.ProductNotFoundException;
import product_api.model.Product;
import product_api.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {
        Long id = idGenerator.incrementAndGet();

        Product product = new Product(
                id,
                request.name(),
                request.price()
        );

        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(
            Long id,
            CreateProductRequest request
    ) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(request.name());
        product.setPrice(request.price());

        return toResponse(productRepository.save(product));
    }

    @Override
    public boolean delete(Long id) {
        return productRepository.deleteById(id);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
