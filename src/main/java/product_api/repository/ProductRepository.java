package product_api.repository;

import product_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceBetween(
            BigDecimal min,
            BigDecimal max
    );

    Optional<Product> findFirstByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

    long countByPriceGreaterThan(BigDecimal price);
}