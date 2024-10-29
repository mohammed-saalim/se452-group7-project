package edu.depaul.cdm.se452.groupProject.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductRepository productRepo;
    
    // finds and returns list of all the categories
    public List<Category> getAllCategories() {
        log.traceEntry("Enter getAllCategories");
        List<Category> categoryList = StreamSupport.stream(categoryRepo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit getAllCategories", categoryList);
        return categoryList;
    }
    
    // finds and returns a category by ID
    public Optional<Category> getCategoryById(Long id) {
        log.traceEntry("Enter getCategoryById", id);
        Optional<Category> category = categoryRepo.findById(id);
        log.traceExit("Exit getCategoryById", category);
        return category;
    }

    // finds and returns a category by name
    public Category getCategoryByName(String name) {
        
        // Using the custom finder method we defined in the CategoryRepository
        log.traceEntry("Enter getCategoryByName");
        Category category = categoryRepo.findByName(name);
        log.traceExit("Exit getCategoryByName", category);
        return category;
    }

    // create a new category
    public Category createCategory(Category category) {
        log.traceEntry("Enter createCategory", category);
        Category savedCategory = categoryRepo.save(category);
        log.info("Category created successfully with ID: {}", savedCategory.getId());
        log.traceExit("Exit createCategory");
        return savedCategory;
    }

    // update an existing category
    public Category updateCategory(Long id, Category updatedCategory) {
        log.traceEntry("Enter updateCategory", id);
        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    Category savedCategory = categoryRepo.save(category);
                    log.info("Category updated successfully with ID: {}", savedCategory.getId());
                    log.traceExit("Exit updateCategory");
                    return savedCategory;
                })
                .orElseThrow(() -> {
                    log.error("Category with ID: {} not found for update", id);

                    // throwing exception when Category is not found
                    return new IllegalArgumentException("Category not found");
                });
    }

    // delete a category by ID
    public void deleteCategory(Long id) {
        log.traceEntry("Enter deleteCategory", id);
        Optional<Category> category = categoryRepo.findById(id);

        if (category.isPresent()) {
            
            // to ensure that all the products associated with this category are also deleted when category is deleted
            List<Product> products = productRepo.findByCategoryName(category.get().getName());
            products.forEach(product -> productRepo.delete(product));

            categoryRepo.deleteById(id);
            log.info("Category deleted successfully with ID: {}", id);
            log.traceExit("Exit deleteCategory");
        } else {
            log.error("Failed to delete category - Category with ID: {} not found", id);

            // throwing exception when Category is not found
            throw new IllegalArgumentException("Category not found");
        }
    }
    
}
