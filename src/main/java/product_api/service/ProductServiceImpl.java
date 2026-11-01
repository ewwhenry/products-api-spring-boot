
package product_api.service;

import product_api.dto.request.CreateProductRequest;
import product_api.dto.response.ProductResponse;
import product_api.exception.ProductNotFoundException;
import product_api.model.Product;
import product_api.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {

        Product product = new Product(
                request.name(),
                request.price()
        );

        Product savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
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

        Product updatedProduct = productRepository.save(product);

        return toResponse(updatedProduct);
    }

    @Override
    public boolean delete(Long id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);

        return true;
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
