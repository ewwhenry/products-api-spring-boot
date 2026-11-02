package product_api.service;

import product_api.dto.response.ProductResponse;
import product_api.dto.request.CreateProductRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse create(CreateProductRequest request);
    ProductResponse update(Long id, CreateProductRequest request);
    void delete(Long id);
    List<ProductResponse> findByName(String name);
    List<ProductResponse> findByPriceBetween(BigDecimal min, BigDecimal max);
    boolean existsByName(String name);
}
