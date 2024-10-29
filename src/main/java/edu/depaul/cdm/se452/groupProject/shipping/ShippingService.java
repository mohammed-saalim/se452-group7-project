package edu.depaul.cdm.se452.groupProject.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ShippingService {

    @Autowired
    private ShippingRepository shippingRepo;

    // Retrieve all shipping records
    public List<Shipping> getAllShippings() {
        log.traceEntry("Enter getAllShippings");
        List<Shipping> shippingList = shippingRepo.findAll();
        log.traceExit("Exit getAllShippings", shippingList);
        return shippingList;
    }

    // Retrieve a specific shipping record by ID
    public Optional<Shipping> getShippingById(Long id) {
        log.traceEntry("Enter getShippingById", id);
        Optional<Shipping> shipping = shippingRepo.findById(id);
        log.traceExit("Exit getShippingById", shipping);
        return shipping;
    }

    // Retrieve all shipping records by order ID
    public List<Shipping> getShippingsByOrderId(Long orderId) {
        log.traceEntry("Enter getShippingsByOrderId", orderId);
        List<Shipping> shippings = shippingRepo.findByOrderId(orderId);
        log.traceExit("Exit getShippingsByOrderId", shippings);
        return shippings;
    }

    // Create or update a shipping record
    public Shipping saveOrUpdateShipping(Shipping shipping) {
        log.traceEntry("Enter saveOrUpdateShipping", shipping);
        Shipping savedShipping = shippingRepo.save(shipping);
        log.info("Shipping saved successfully with ID: {}", savedShipping.getId());
        log.traceExit("Exit saveOrUpdateShipping");
        return savedShipping;
    }

    // Delete a shipping record by ID
    public void deleteShipping(Long id) {
        log.traceEntry("Enter deleteShipping", id);
        if (shippingRepo.existsById(id)) {
            shippingRepo.deleteById(id);
            log.info("Shipping deleted successfully with ID: {}", id);
        } else {
            log.error("Shipping with ID: {} not found for deletion", id);
            throw new IllegalArgumentException("Shipping not found");
        }
        log.traceExit("Exit deleteShipping");
    }
}
