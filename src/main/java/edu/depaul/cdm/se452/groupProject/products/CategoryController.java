package edu.depaul.cdm.se452.groupProject.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/categories") // Base URL for category-related endpoints
@Tag(name = "Category", description = "Everything about Product Categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET /api/categories - Retrieve all categories
    @GetMapping
    @Operation(summary = "Returns all the product categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // GET /api/categories/{id} - Retrieve a category by ID
    @GetMapping("/{id}")
    @Operation(summary = "Returns the product category by category ID")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // GET /api/categories/name/{name} - Retrieve a category by name
    @GetMapping("/name/{name}")
    @Operation(summary = "Returns the product category by name")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // POST /api/categories - Create a new category
    @PostMapping
    @Operation(summary = "Create a new product category")
    // Sample Category Creation JSON Input:
    // {
    //     "id": 0,
    //     "name": "sports",
    //     "products": []
    // }
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    // PUT /api/categories/{id} - Update an existing category
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product category")
    // Sample Category Updation JSON Input:
    // {
    //     "id": 4,
    //     "name": "sports wear",
    //     "products": []
    // }
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id, @RequestBody Category updatedCategory) {
        try {
            Category category = categoryService.updateCategory(id, updatedCategory);
            return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE /api/categories/{id} - Delete a category by ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product category")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
