package edu.depaul.cdm.se452.groupProject.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Custom finder to find category by name
    public Category findByName(String name);
    
}