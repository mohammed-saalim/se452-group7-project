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
import edu.depaul.cdm.se452.groupProject.products.ProductRepository;

@SpringBootTest
public class CategoryServiceTest {

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
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Electronics");

        Category savedCategory = categoryService.createCategory(category);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals("Electronics", savedCategory.getName());
    }

    @Test
    public void testGetAllCategories() {
        // Create a category
        Category category1 = new Category();
        category1.setName("Home and Kitchen");
        Category savedCategory1 = categoryService.createCategory(category1);
 
        Category category2 = new Category();
        category2.setName("Toys");
        Category savedCategory2 = categoryService.createCategory(category2);
        

        List<Category> retrievedCategories = categoryService.getAllCategories();
        assertNotNull(retrievedCategories);
        assertEquals(savedCategory1.getName(), retrievedCategories.get(0).getName());
        assertEquals(savedCategory2.getName(), retrievedCategories.get(1).getName());
        assertEquals(2, retrievedCategories.size());
    }


    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setName("Electronics");

        Category savedCategory = categoryService.createCategory(category);

        Optional<Category> retrievedCategory = categoryService.getCategoryById(savedCategory.getId());
        assertTrue(retrievedCategory.isPresent());
        assertEquals("Electronics", retrievedCategory.get().getName());
    }

    @Test
    public void testGetCategoryByName() {
        Category category = new Category();
        category.setName("Toys");

        categoryService.createCategory(category);

        Category retrievedCategory = categoryService.getCategoryByName("Toys");
        assertNotNull(retrievedCategory);
        assertEquals("Toys", retrievedCategory.getName());
    }
    
    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Clothing");

        Category savedCategory = categoryService.createCategory(category);

        // Update category
        savedCategory.setName("Apparel");
        Category updatedCategory = categoryService.updateCategory(savedCategory.getId(), savedCategory);

        assertEquals("Apparel", updatedCategory.getName());
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Gardening");

        Category savedCategory = categoryService.createCategory(category);
        Long categoryId = savedCategory.getId();

        categoryService.deleteCategory(categoryId);

        Optional<Category> deletedCategory = categoryService.getCategoryById(categoryId);
        assertFalse(deletedCategory.isPresent());
    }
}
