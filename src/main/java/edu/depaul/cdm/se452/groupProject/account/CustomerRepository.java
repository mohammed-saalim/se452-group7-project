package edu.depaul.cdm.se452.groupProject.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom finder to find customer by email
    Customer findByEmail(String email);
}
