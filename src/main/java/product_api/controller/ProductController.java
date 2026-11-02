package product_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import product_api.dto.request.CreateProductRequest;
import product_api.dto.response.ApiResponse;
import product_api.dto.response.ProductResponse;
import product_api.service.ProductService;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody CreateProductRequest request
    ) {
        ProductResponse product = productService.create(request);

        return ResponseEntity
                .created(URI.create("/api/products/" + product.id()))
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody CreateProductRequest request
    ) {
        return ResponseEntity.ok(
                productService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductResponse>> filterByPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return ResponseEntity.ok(productService.findByPriceBetween(min, max));
    }

    @GetMapping("/exists")
    public ResponseEntity<ApiResponse<Boolean>> exists (@RequestParam String name) {
        return ResponseEntity.ok(new ApiResponse<>(
                productService.existsByName(name),
                "Product existence checked",
                "success"
        ));
    }
}
