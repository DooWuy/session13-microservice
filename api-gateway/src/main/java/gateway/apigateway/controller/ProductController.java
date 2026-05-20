package gateway.apigateway.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

public class ProductController {

    @Data
    public static class Product {
        private Long id;
        private String name;
        private Double price;

        public Product(Long id, String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Laptop", 999.99),
                new Product(2L, "Mouse", 29.99),
                new Product(3L, "Keyboard", 79.99)
        );
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(Long id) {
        Product product = new Product(id, "Sample Product", 49.99);
        return ResponseEntity.ok(product);
    }
}
