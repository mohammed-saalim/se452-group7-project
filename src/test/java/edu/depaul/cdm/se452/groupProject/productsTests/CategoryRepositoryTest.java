package edu.depaul.cdm.se452.groupProject.productsTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.products.Category;
import edu.depaul.cdm.se452.groupProject.products.CategoryRepository;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepo;

    @BeforeEach
    void cleanDatabase() {
        categoryRepo.deleteAll();
    }

    @Test
    public void testSaveCategory() {
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category savedCategory = categoryRepo.save(electronics);

        Optional<Category> retrievedCategory = categoryRepo.findById(savedCategory.getId());
        assertNotNull(retrievedCategory);
        assertEquals(retrievedCategory.get().getName(),"Electronics");
    }

    @Test
    public void testFindByName() {
        Category clothing = new Category();
        clothing.setName("Clothing");
        categoryRepo.save(clothing);

        Category retrievedCategory = categoryRepo.findByName("Clothing");
        assertNotNull(retrievedCategory);
        assertEquals(retrievedCategory.getName(), "Clothing");
    }
    
}
