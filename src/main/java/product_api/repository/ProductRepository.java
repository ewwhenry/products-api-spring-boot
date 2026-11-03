package product_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import product_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
                SELECT p
                FROM Product p
                WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Product> findByName(@Param("name") String name);

    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("""
                SELECT p
                FROM Product p
                WHERE p.price BETWEEN :min AND :max
            """)
    List<Product> findByPriceRange(
            @Param("min") BigDecimal min,
            @Param("max") BigDecimal max
    );

    @Query("""
                SELECT p
                FROM Product p
                WHERE p.price > (
                    SELECT AVG(p2.price)
                    FROM Product p2
                )
            """)
    List<Product> findAboveAveragePrice();

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceBetween(
            BigDecimal min,
            BigDecimal max
    );

    Optional<Product> findFirstByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

    long countByPriceGreaterThan(BigDecimal price);
}