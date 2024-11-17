package edu.depaul.cdm.se452.groupProject.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductService {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    // finds and returns list of all the products
    public List<Product> getAllProducts() {
        log.traceEntry("Enter getAllProducts");
        List<Product> productList = StreamSupport.stream(productRepo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit getAllProducts", productList);
        return productList;
    }
    
    // finds and returns a product by ID
    public Optional<Product> getProductById(Long id) {
        log.traceEntry("Enter getProductById", id);
        Optional<Product> product = productRepo.findById(id);
        log.traceExit("Exit getProductById", product);
        return product;
    }
    
    // finds and returns a product by category name
    public List<Product> getProductsByCategoryName(String categoryName) {
        
        // Using the custom finder method we defined in the ProductRepository
        log.traceEntry("Enter getProductsByCategoryName");
        List<Product> products = productRepo.findByCategoryName(categoryName);
        log.traceExit("Exit getProductsByCategoryName", products);
        return products;
    }

    // create a new product
    public Product createProduct(Product product) {
        log.traceEntry("Enter createProduct", product);

        if (product.getCategory() == null || product.getCategory().getId() == null) {
            log.error("Category or category ID is null in the product input");
            throw new IllegalArgumentException("Category and category ID must not be null");
        }

        // to ensure that the category exists before assigning it to the product
        Optional<Category> category = categoryRepo.findById(product.getCategory().getId());
        if (category.isPresent()) {
            product.setCategory(category.get());
            
            Product savedProduct = productRepo.save(product);
            
            log.info("Product created successfully with ID: {}", savedProduct.getId());
            log.traceExit("Exit createProduct");
            return savedProduct;
        } else {
            log.error("Failed to create product - Category not found");
            
            // throwing exception when category is not found
            throw new IllegalArgumentException("Category not found");
        }
    }   

    // update an existing product
    public Product updateProduct(Long id, Product updatedProduct) {
        log.traceEntry("Enter updateProduct", id);
        return productRepo.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());

                    if (updatedProduct.getCategory() != null) {
                        Optional<Category> category = categoryRepo.findById(updatedProduct.getCategory().getId());
                        
                        // using method reference operator :: to call the setCategory method on product with the argument provided by ifPresent
                        category.ifPresent(product::setCategory);
                    }
                    
                    Product savedProduct = productRepo.save(product);
                    
                    log.info("Product updated successfully with ID: {}", savedProduct.getId());
                    log.traceExit("Exit updateProduct");
                    return savedProduct;
                })
                .orElseThrow(() -> {
                    log.error("Product with ID: {} not found for update", id);
                    
                    // throwing exception when Product is not found
                    return new IllegalArgumentException("Product not found");
                });
    }

    // delete a product by ID
    public void deleteProduct(Long id) {
        log.traceEntry("Enter deleteProduct", id);

        // to check if the product exists by using its id and then deleting it if it exists 
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            log.info("Product deleted successfully with ID: {}", id);
            log.traceExit("Exit deleteProduct");
        } else {
            log.error("Failed to delete product - Product with ID: {} not found", id);

            // throwing exception when Product is not found
            throw new IllegalArgumentException("Product not found");
        }
    }

}
