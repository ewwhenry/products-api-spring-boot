package product_api.repository;

import product_api.model.Product;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new LinkedHashMap<>();

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public boolean deleteById(Long id) {
        return products.remove(id) != null;
    }
}
