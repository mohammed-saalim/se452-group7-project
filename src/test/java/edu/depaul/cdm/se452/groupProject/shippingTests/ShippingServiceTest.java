package edu.depaul.cdm.se452.groupProject.shippingTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.shipping.Shipping;
import edu.depaul.cdm.se452.groupProject.shipping.ShippingService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShippingServiceTest {

    @Autowired
    private ShippingService shippingService;

    @Test
    public void testSaveOrUpdateShipping() {
        Shipping shipping = new Shipping();
        shipping.setStatus("Pending");
        shipping.setTrackingNumber("TRK123456");
        shipping.setOrderId(1L);

        Shipping savedShipping = shippingService.saveOrUpdateShipping(shipping);
        assertNotNull(savedShipping);
        assertEquals("Pending", savedShipping.getStatus());
    }

    @Test
    public void testGetAllShippings() {
        List<Shipping> shippingList = shippingService.getAllShippings();
        assertNotNull(shippingList);
        assertTrue(shippingList.size() >= 0);
    }

    @Test
    public void testGetShippingById() {
        Shipping shipping = new Shipping();
        shipping.setStatus("Shipped");
        shipping.setTrackingNumber("TRK7891011");
        shipping.setOrderId(2L);

        Shipping savedShipping = shippingService.saveOrUpdateShipping(shipping);
        Optional<Shipping> foundShipping = shippingService.getShippingById(savedShipping.getId());

        assertTrue(foundShipping.isPresent());
        assertEquals("Shipped", foundShipping.get().getStatus());
    }

    @Test
    public void testGetShippingsByOrderId() {
        Long orderId = 3L;
        
        Shipping shipping1 = new Shipping();
        shipping1.setStatus("In Transit");
        shipping1.setTrackingNumber("TRK111213");
        shipping1.setOrderId(orderId);
        shippingService.saveOrUpdateShipping(shipping1);

        Shipping shipping2 = new Shipping();
        shipping2.setStatus("Delivered");
        shipping2.setTrackingNumber("TRK141516");
        shipping2.setOrderId(orderId);
        shippingService.saveOrUpdateShipping(shipping2);

        List<Shipping> shippings = shippingService.getShippingsByOrderId(orderId);
        assertNotNull(shippings);
        assertEquals(2, shippings.size());
    }

    @Test
    public void testDeleteShipping() {
        Shipping shipping = new Shipping();
        shipping.setStatus("Canceled");
        shipping.setTrackingNumber("TRK171819");
        shipping.setOrderId(4L);

        Shipping savedShipping = shippingService.saveOrUpdateShipping(shipping);
        Long id = savedShipping.getId();

        shippingService.deleteShipping(id);
        Optional<Shipping> deletedShipping = shippingService.getShippingById(id);
        assertFalse(deletedShipping.isPresent());
    }
}
