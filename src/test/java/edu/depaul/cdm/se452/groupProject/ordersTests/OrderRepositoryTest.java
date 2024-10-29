package edu.depaul.cdm.se452.groupProject.ordersTests;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
 
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
 
import edu.depaul.cdm.se452.groupProject.orders.CustomerOrder;
import edu.depaul.cdm.se452.groupProject.orders.OrderRepository;
import edu.depaul.cdm.se452.groupProject.payments.PaymentRepository;
import edu.depaul.cdm.se452.groupProject.account.Customer;
import edu.depaul.cdm.se452.groupProject.account.CustomerRepository;
 
@SpringBootTest
public class OrderRepositoryTest {
 
    @Autowired
    private OrderRepository orderRepository;
 
    @Autowired
    private CustomerRepository customerRepository;
 
    @Autowired
    private PaymentRepository paymentRepository;
 
    @BeforeEach
    void cleanDatabase() {
        paymentRepository.deleteAll();
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }
 
    @Test
    public void testFindByCustomerCustomerId() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customerRepository.save(customer);
 
        CustomerOrder order = new CustomerOrder();
        order.setTotalAmount(100.0);
        order.setOrderDate("2024-10-14");
        order.setCustomer(customer);
        orderRepository.save(order);
 
        List<CustomerOrder> orders = orderRepository.findByCustomer_CustomerId(customer.getCustomerId());
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(100.0, orders.get(0).getTotalAmount());
    }
}