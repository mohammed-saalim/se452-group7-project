package edu.depaul.cdm.se452.groupProject.ordersTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.orders.Cart;
import edu.depaul.cdm.se452.groupProject.orders.CartRepository;
import edu.depaul.cdm.se452.groupProject.orders.OrderRepository;
import edu.depaul.cdm.se452.groupProject.payments.PaymentRepository;
import edu.depaul.cdm.se452.groupProject.account.Customer;
import edu.depaul.cdm.se452.groupProject.account.CustomerRepository;

@SpringBootTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
     @Autowired
    private PaymentRepository paymentRepository;
   
    @BeforeEach
    void cleanDatabase() {
        paymentRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testFindByCustomerCustomerId() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setEmail("jane.smith@example.com");
        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setTotalAmount(59.99);
        cart.setCustomer(customer);
        cartRepository.save(cart);

        List<Cart> carts = cartRepository.findByCustomer_CustomerId(customer.getCustomerId());
        assertNotNull(carts);
        assertEquals(1, carts.size());
        assertEquals(59.99, carts.get(0).getTotalAmount());
    }
}
