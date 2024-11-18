package edu.depaul.cdm.se452.groupProject.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shippings")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    // Retrieve all shipping records
    @GetMapping
    public ResponseEntity<List<Shipping>> getAllShippings() {
        List<Shipping> shippings = shippingService.getAllShippings();
        return ResponseEntity.ok(shippings);
    }

    // Retrieve a specific shipping record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shipping> getShippingById(@PathVariable Long id) {
        Optional<Shipping> shipping = shippingService.getShippingById(id);
        if (shipping.isPresent()) {
            return ResponseEntity.ok(shipping.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Retrieve all shipping records by order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Shipping>> getShippingsByOrderId(@PathVariable Long orderId) {
        List<Shipping> shippings = shippingService.getShippingsByOrderId(orderId);
        if (!shippings.isEmpty()) {
            return ResponseEntity.ok(shippings);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Create or update a shipping record
    @PostMapping
    public ResponseEntity<Shipping> createOrUpdateShipping(@RequestBody Shipping shipping) {
        Shipping savedShipping = shippingService.saveOrUpdateShipping(shipping);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShipping);
    }

    // Delete a shipping record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipping(@PathVariable Long id) {
        try {
            shippingService.deleteShipping(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
