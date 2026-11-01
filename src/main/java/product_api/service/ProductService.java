package product_api.service;

import product_api.dto.response.ProductResponse;
import product_api.dto.request.CreateProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse create(CreateProductRequest request);
    ProductResponse update(Long id, CreateProductRequest request);
    boolean delete(Long id);
}
