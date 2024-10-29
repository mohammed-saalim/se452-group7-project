package edu.depaul.cdm.se452.groupProject.productsTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.orders.CartRepository;
import edu.depaul.cdm.se452.groupProject.products.Category;
import edu.depaul.cdm.se452.groupProject.products.CategoryRepository;
import edu.depaul.cdm.se452.groupProject.products.Product;
import edu.depaul.cdm.se452.groupProject.products.ProductRepository;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepo;
    
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private CartRepository cartRepo;

    @BeforeEach
    void cleanDatabase() {
        cartRepo.deleteAll();
        productRepo.deleteAll();
        categoryRepo.deleteAll();
    }

    @Test
    public void testFindByName() {
        Category electronics = new Category();
        electronics.setName("Electronics");
        categoryRepo.save(electronics);

        Product smartphone = new Product();
        smartphone.setName("Smartphone");
        smartphone.setDescription("Bestselling and latest smartphone");
        smartphone.setPrice(899.99);
        smartphone.setCategory(electronics);
        productRepo.save(smartphone);

        Product retrievedProduct = productRepo.findByName("Smartphone");
        assertNotNull(retrievedProduct);
        assertEquals("Smartphone", retrievedProduct.getName());
    }

    @Test
    public void testFindByCategoryName() {

        Category clothing = new Category();
		clothing.setName("Clothing");
		categoryRepo.save(clothing);
       
        Product jeans = new Product();
		jeans.setName("Jeans");
        jeans.setDescription("Stylish and durable denim jeans");
        jeans.setPrice(39.99);
        jeans.setCategory(clothing);
		productRepo.save(jeans);
		
        List<Product> retrievedProducts = productRepo.findByCategoryName("Clothing");
        assertNotNull(retrievedProducts);
        assertEquals("Jeans", retrievedProducts.get(0).getName());

    }

    @Test
    public void testProductCategoryRelationship() {
        Category electronics = new Category();
        electronics.setName("Electronics");
        categoryRepo.save(electronics);

        Product smartphone = new Product();
        smartphone.setName("Smartphone");
        smartphone.setDescription("Bestselling and latest smartphone");
        smartphone.setPrice(899.99);
        smartphone.setCategory(electronics);
        productRepo.save(smartphone);

        Product laptop = new Product();
        laptop.setName("Laptop");
        laptop.setDescription("Bestselling and latest laptop");
        laptop.setPrice(1399.99);
        laptop.setCategory(electronics);
        productRepo.save(laptop);

        List<Product> retrievedProducts = productRepo.findAll();
        assertNotNull(retrievedProducts);
        assertEquals("Smartphone", retrievedProducts.get(0).getName());
        assertEquals("Laptop", retrievedProducts.get(1).getName());
    }

}