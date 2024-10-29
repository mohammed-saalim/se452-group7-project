package edu.depaul.cdm.se452.groupProject.accountTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.account.Customer;
import edu.depaul.cdm.se452.groupProject.account.CustomerRepository;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepo;

    @BeforeEach
    void cleanDatabase() {
        customerRepo.deleteAll();
    }

    @Test
    public void testSaveCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setUsername("jdoe");
        customer.setPassword("qwerty");

        Customer savedCustomer = customerRepo.save(customer);
        Optional<Customer> retrievedCustomer = customerRepo.findById(savedCustomer.getCustomerId());

        assertNotNull(retrievedCustomer);
        assertEquals(retrievedCustomer.get().getFirstName(), "John");
    }

    @Test
    public void testFindById() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setUsername("jdoe");
        customer.setPassword("qwerty");
        Customer savedCustomer = customerRepo.save(customer);

        Optional<Customer> retrievedCustomer = customerRepo.findById(savedCustomer.getCustomerId());
        assertNotNull(retrievedCustomer);
        assertEquals(retrievedCustomer.get().getFirstName(), "John");
    }

}
