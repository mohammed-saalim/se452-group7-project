package edu.depaul.cdm.se452.groupProject.shippingTests;

import edu.depaul.cdm.se452.groupProject.shipping.Shipping;
import edu.depaul.cdm.se452.groupProject.shipping.ShippingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ShippingRepositoryTest {

    @Autowired
    private ShippingRepository shippingRepository;

    @Test
    public void testAddShipping() {
        Shipping shipping = new Shipping();
        shipping.setStatus("Pending");
        shipping.setTrackingNumber("TRK123456789");
        shipping.setOrderId(1L);

        Shipping savedShipping = shippingRepository.save(shipping);
        assertEquals("Pending", savedShipping.getStatus());
    }
}
