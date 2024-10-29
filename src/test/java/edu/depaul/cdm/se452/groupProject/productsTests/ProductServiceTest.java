package edu.depaul.cdm.se452.groupProject.productsTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.orders.CartRepository;
import edu.depaul.cdm.se452.groupProject.products.Category;
import edu.depaul.cdm.se452.groupProject.products.CategoryRepository;
import edu.depaul.cdm.se452.groupProject.products.CategoryService;
import edu.depaul.cdm.se452.groupProject.products.Product;
import edu.depaul.cdm.se452.groupProject.products.ProductRepository;
import edu.depaul.cdm.se452.groupProject.products.ProductService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private CartRepository cartRepo;

    @BeforeEach
    public void setup() {
        // Clear all existing data
        cartRepo.deleteAll();
        productRepo.deleteAll();
        categoryRepo.deleteAll();
    }

    @Test
    public void testCreateProduct() {
        // First create a category
        Category category = new Category();
        category.setName("Electronics");
        Category savedCategory = categoryService.createCategory(category);

        // Create a product
        Product product = new Product();
        product.setName("Smartphone");
        product.setDescription("A brand new smartphone");
        product.setPrice(899.99);
        product.setCategory(savedCategory);

        Product savedProduct = productService.createProduct(product);
        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());
        assertEquals("Smartphone", savedProduct.getName());
    }

    @Test
    public void testGetAllProducts() {
        // Create a category
        Category category = new Category();
        category.setName("Home and Kitchen");
        Category savedCategory = categoryService.createCategory(category);

        // Create products
        Product product1 = new Product();
        product1.setName("Blender");
        product1.setDescription("High-power blender");
        product1.setPrice(99.99);
        product1.setCategory(savedCategory);

        Product product2 = new Product();
        product2.setName("Coffee Maker");
        product2.setDescription("High-power automatic coffee maker");
        product2.setPrice(199.99);
        product2.setCategory(savedCategory);

        Product savedProduct1 = productService.createProduct(product1);
        Product savedProduct2 = productService.createProduct(product2);

        List<Product> retrievedProductsList = productService.getAllProducts();
        assertNotNull(retrievedProductsList);
        assertTrue(retrievedProductsList.contains(savedProduct1));
        assertTrue(retrievedProductsList.contains(savedProduct2));
        assertEquals(2, retrievedProductsList.size());
    }

    @Test
    public void testGetProductById() {
        // Create a category
        Category category = new Category();
        category.setName("Home and Kitchen");
        Category savedCategory = categoryService.createCategory(category);

        // Create a product
        Product product = new Product();
        product.setName("Blender");
        product.setDescription("High-power blender");
        product.setPrice(99.99);
        product.setCategory(savedCategory);

        Product savedProduct = productService.createProduct(product);

        Optional<Product> retrievedProduct = productService.getProductById(savedProduct.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Blender", retrievedProduct.get().getName());
    }

    @Test
    public void testGetProductsByCategoryName() {
        // Create and save a category
        Category category = new Category();
        category.setName("Electronics");
        Category savedCategory = categoryService.createCategory(category);

        // Create and save products in the "Electronics" category
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setDescription("High-performance laptop");
        product1.setPrice(1299.99);
        product1.setCategory(savedCategory);
        productService.createProduct(product1);

        Product product2 = new Product();
        product2.setName("Smartphone");
        product2.setDescription("Latest model smartphone");
        product2.setPrice(899.99);
        product2.setCategory(savedCategory);
        productService.createProduct(product2);

        // Fetch products by category name
        List<Product> products = productService.getProductsByCategoryName("Electronics");

        assertNotNull(products);
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }

    @Test
    public void testUpdateProduct() {
        // Create a category
        Category category = new Category();
        category.setName("Electronics");
        Category savedCategory = categoryService.createCategory(category);

        // Create a product
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Gaming laptop");
        product.setPrice(1299.99);
        product.setCategory(savedCategory);

        Product savedProduct = productService.createProduct(product);

        // Update product
        savedProduct.setName("Gaming Laptop Pro");
        Product updatedProduct = productService.updateProduct(savedProduct.getId(), savedProduct);

        assertEquals("Gaming Laptop Pro", updatedProduct.getName());
    }

    @Test
    public void testDeleteProduct() {
        // Create a category
        Category category = new Category();
        category.setName("Electronics");
        Category savedCategory = categoryService.createCategory(category);

        // Create a product
        Product product = new Product();
        product.setName("Headphones");
        product.setDescription("Noise-cancelling headphones");
        product.setPrice(199.99);
        product.setCategory(savedCategory);

        Product savedProduct = productService.createProduct(product);
        Long productId = savedProduct.getId();

        productService.deleteProduct(productId);

        Optional<Product> deletedProduct = productService.getProductById(productId);
        assertFalse(deletedProduct.isPresent());
    }

}
