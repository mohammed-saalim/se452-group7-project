package edu.depaul.cdm.se452.groupProject.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/products") // Base URL for product-related endpoints
@Tag(name = "Product", description = "Everything about Products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/products - Retrieve all products
    @GetMapping
    @Operation(summary = "Returns all the products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // GET /api/products/{id} - Retrieve a product by ID
    @GetMapping("/{id}")
    @Operation(summary = "Returns the product by product ID")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // GET /api/products/category/{categoryName} - Retrieve products by category name
    @GetMapping("/category/{categoryName}")
    @Operation(summary = "Returns products by category name")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategoryName(categoryName);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // POST /api/products - Create a new product
    @PostMapping
    @Operation(summary = "Create a new product")
    // Sample Product Creation JSON Input:
    // {
    //     "id": 0,
    //     "name": "ipad",
    //     "description": "latest apple ipad",
    //     "price": 899.99,
    //     "category": {
    //       "id": 2,
    //       "name": "Electronics"
    //     }
    //   }
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // PUT /api/products/{id} - Update an existing product
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    // Sample Product Updation JSON Input:
    // {
    //     "id": 0,
    //     "name": "ipad",
    //     "description": "latest apple ipad",
    //     "price": 1299.99,
    //     "category": {
    //       "id": 2,
    //       "name": "Electronics"
    //     }
    //   }
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            Product product = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE /api/products/{id} - Delete a product by ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
