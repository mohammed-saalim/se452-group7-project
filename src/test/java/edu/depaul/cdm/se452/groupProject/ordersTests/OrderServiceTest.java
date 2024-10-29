package edu.depaul.cdm.se452.groupProject.ordersTests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.orders.CustomerOrder;
import edu.depaul.cdm.se452.groupProject.orders.OrderRepository;
import edu.depaul.cdm.se452.groupProject.orders.OrderService;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepo;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        when(orderRepo.findAll()).thenReturn(List.of(new CustomerOrder()));
        List<CustomerOrder> orders = orderService.getAllOrders();
        assertFalse(orders.isEmpty());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        CustomerOrder order = new CustomerOrder();
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        Optional<CustomerOrder> foundOrder = orderService.getOrderById(1L);
        assertTrue(foundOrder.isPresent());
        verify(orderRepo, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrder() {
        CustomerOrder order = new CustomerOrder();
        when(orderRepo.save(order)).thenReturn(order);
        CustomerOrder savedOrder = orderService.createOrder(order);
        assertNotNull(savedOrder);
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    public void testDeleteOrder() {
        when(orderRepo.existsById(1L)).thenReturn(true);
        orderService.deleteOrder(1L);
        verify(orderRepo, times(1)).deleteById(1L);
    }
}
