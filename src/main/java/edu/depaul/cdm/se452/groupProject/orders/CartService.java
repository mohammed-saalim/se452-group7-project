package edu.depaul.cdm.se452.groupProject.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    // Retrieve all carts
    public List<Cart> getAllCarts() {
        log.traceEntry("Enter getAllCarts");
        List<Cart> carts = cartRepo.findAll();
        log.traceExit("Exit getAllCarts", carts);
        return carts;
    }

    // Retrieve a specific cart by ID
    public Optional<Cart> getCartById(Long id) {
        log.traceEntry("Enter getCartById", id);
        Optional<Cart> cart = cartRepo.findById(id);
        log.traceExit("Exit getCartById", cart);
        return cart;
    }

    // Retrieve carts by customer ID
    public List<Cart> getCartsByCustomerId(Long customerId) {
        log.traceEntry("Enter getCartsByCustomerId", customerId);
        List<Cart> carts = cartRepo.findByCustomer_CustomerId(customerId);
        log.traceExit("Exit getCartsByCustomerId", carts);
        return carts;
    }

    // Create or update a cart
    public Cart saveOrUpdateCart(Cart cart) {
        log.traceEntry("Enter saveOrUpdateCart", cart);
        Cart savedCart = cartRepo.save(cart);
        log.info("Cart saved successfully with ID: {}", savedCart.getId());
        log.traceExit("Exit saveOrUpdateCart");
        return savedCart;
    }

    // Delete a cart by ID
    public void deleteCart(Long id) {
        log.traceEntry("Enter deleteCart", id);
        if (cartRepo.existsById(id)) {
            cartRepo.deleteById(id);
            log.info("Cart deleted successfully with ID: {}", id);
        } else {
            log.error("Cart with ID: {} not found for deletion", id);
            throw new IllegalArgumentException("Cart not found");
        }
        log.traceExit("Exit deleteCart");
    }
}
