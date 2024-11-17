package edu.depaul.cdm.se452.groupProject.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart", description = "Everything about Carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    @Operation(summary = "Retrieve all carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a cart by ID")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Retrieve carts by customer ID")
    public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable Long customerId) {
        List<Cart> carts = cartService.getCartsByCustomerId(customerId);
        if (!carts.isEmpty()) {
            return ResponseEntity.ok(carts);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Operation(summary = "Create or update a cart")
    // Sample Input for Cart Creation { Swagger }
    // {
    //     "id": 0,
    //     "totalAmount": 29.99,
    //     "customer": {
    //       "customerId": 106,
    //       "firstName": "David",
    //       "lastName": "Wilson",
    //       "email": "david.wilson@gmail.com",
    //       "username": "dwilson106",
    //       "password": "DW@77889"
    //       },
    //     "cartItems": [
    //       {
    //         "price": 29.99,
    //         "quantity": 1,
    //         "product": {
    //           "id": 6,
    //           "name": "Backpack",
    //           "description": "Durable and spacious backpack",
    //           "price": 29.99
    //         }
    //       }
    //     ],
    //     "customerId": 106
    //   }
      
    public ResponseEntity<Cart> createOrUpdateCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveOrUpdateCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart by ID")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        try {
            cartService.deleteCart(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
