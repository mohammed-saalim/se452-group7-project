package edu.depaul.cdm.se452.groupProject.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer_CustomerId(Long customerId);  // Use customerId through the Customer reference
}
