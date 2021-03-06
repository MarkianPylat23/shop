package shop.dao;

import shop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    List<Product> findAll();

    Optional<Product> findProductByName(String name);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);
}