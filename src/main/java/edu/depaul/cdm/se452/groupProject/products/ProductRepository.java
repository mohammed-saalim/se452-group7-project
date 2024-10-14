package edu.depaul.cdm.se452.groupProject.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom finder to find product by name
    public Product findByName(String name);
    // Custom finder to find products by category name
    public List<Product> findByCategoryName(String categoryName);
    
}