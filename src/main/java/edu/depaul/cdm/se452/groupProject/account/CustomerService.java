package edu.depaul.cdm.se452.groupProject.account;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customerDetails);

    void deleteCustomer(Long id);

    Customer findByEmail(String email);
}
