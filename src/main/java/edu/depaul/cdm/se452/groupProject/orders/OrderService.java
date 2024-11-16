package edu.depaul.cdm.se452.groupProject.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Log4j2
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public List<CustomerOrder> getAllOrders() {
        log.traceEntry("Enter getAllOrders");
        List<CustomerOrder> orderList = StreamSupport
                .stream(orderRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        log.traceExit("Exit getAllOrders", orderList);
        return orderList;
    }

    public Optional<CustomerOrder> getOrderById(Long id) {
        log.traceEntry("Enter getOrderById", id);
        Optional<CustomerOrder> order = orderRepo.findById(id);
        log.traceExit("Exit getOrderById", order);
        return order;
    }

    public List<CustomerOrder> getOrdersByCustomerId(Long customerId) {
        log.traceEntry("Enter getOrdersByCustomerId", customerId);
        List<CustomerOrder> orders = orderRepo.findByCustomer_CustomerId(customerId);
        log.traceExit("Exit getOrdersByCustomerId", orders);
        return orders;
    }

    public CustomerOrder createOrder(CustomerOrder order) {
        log.traceEntry("Enter createOrder", order);
        CustomerOrder savedOrder = orderRepo.save(order);
        log.info("Order created successfully with ID: {}", savedOrder.getId());
        log.traceExit("Exit createOrder");
        return savedOrder;
    }

    public CustomerOrder updateOrder(Long id, CustomerOrder updatedOrder) {
        log.traceEntry("Enter updateOrder", id);
        return orderRepo.findById(id)
                .map(order -> {
                    order.setOrderDate(updatedOrder.getOrderDate());
                    order.setTotalAmount(updatedOrder.getTotalAmount());
                    CustomerOrder savedOrder = orderRepo.save(order);
                    log.info("Order updated successfully with ID: {}", savedOrder.getId());
                    log.traceExit("Exit updateOrder");
                    return savedOrder;
                })
                .orElseThrow(() -> {
                    log.error("Order with ID: {} not found for update", id);
                    return new IllegalArgumentException("Order not found");
                });
    }

    public void deleteOrder(Long id) {
        log.traceEntry("Enter deleteOrder", id);
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
            log.info("Order deleted successfully with ID: {}", id);
            log.traceExit("Exit deleteOrder");
        } else {
            log.error("Failed to delete order - Order with ID: {} not found", id);
            throw new IllegalArgumentException("Order not found");
        }
    }

    // Author: Bhumika Ramesh
    // Added this function to perform customer deletion operation on cascading
    // relationships
    public void deleteCustomerOrder(Long id) {
        CustomerOrder order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        // Clear related payments if needed
        if (order.getPayments() != null) {
            order.getPayments().clear();
        }
        orderRepo.delete(order);
    }
}
