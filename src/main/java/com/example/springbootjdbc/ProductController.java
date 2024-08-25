package com.example.springbootjdbc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/fetch-product")
    public ResponseEntity<String> fetchProduct(@RequestParam String name) {
        try {
            String productName = productRepository.getProductName(name);
            if (productName != null) {
                return ResponseEntity.ok(productName);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found for name: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
