package edu.depaul.cdm.se452.groupProject.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Everything about Orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Returns all orders")
    public ResponseEntity<List<CustomerOrder>> getAllOrders() {
        List<CustomerOrder> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns the order by ID")
    public ResponseEntity<CustomerOrder> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Returns orders by customer ID")
    public ResponseEntity<List<CustomerOrder>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<CustomerOrder> orders = orderService.getOrdersByCustomerId(customerId);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Operation(summary = "Create a new order") 
    // Sample Input for Order Creation { Swagger }
    // {
    //     "id": 0,
    //     "orderDate": "2024-10-07",
    //     "totalAmount": 9,
    //     "customer": {
    //       "customerId": 101,
    //       "firstName": "Raanjhana",
    //       "lastName": "Doe",
    //       "email": "raanjhana.doe@example.com",
    //       "username": "raanjhana101",
    //       "password": "password123"
    //     },
    //     "payments": [
    //       {
    //         "id": 0,
    //         "paymentMethod": "CREDIT_CARD",
    //         "amount": 9,
    //         "status": "SUCCESS",
    //         "order": {
    //           "id": 3,
    //           "orderDate": "2024-10-06",
    //           "totalAmount": 50.5,
    //           "customer": {
    //             "customerId": 102,
    //             "firstName": "John",
    //             "lastName": "Smith",
    //             "email": "john.smith@example.com",
    //             "username": "johns102",
    //             "password": "pass123"
    //           }
    //         }
    //       }
    //     ]
    //   }
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order) {
        try {
            CustomerOrder createdOrder = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing order")
    public ResponseEntity<CustomerOrder> updateOrder(@PathVariable Long id, @RequestBody CustomerOrder updatedOrder) {
        try {
            CustomerOrder order = orderService.updateOrder(id, updatedOrder);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
