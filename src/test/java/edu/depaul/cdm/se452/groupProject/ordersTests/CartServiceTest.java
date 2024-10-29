package edu.depaul.cdm.se452.groupProject.ordersTests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.groupProject.orders.Cart;
import edu.depaul.cdm.se452.groupProject.orders.CartRepository;
import edu.depaul.cdm.se452.groupProject.orders.CartService;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepo;

    public CartServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCarts() {
        when(cartRepo.findAll()).thenReturn(List.of(new Cart()));
        List<Cart> carts = cartService.getAllCarts();
        assertFalse(carts.isEmpty());
        verify(cartRepo, times(1)).findAll();
    }

    @Test
    public void testGetCartById() {
        Cart cart = new Cart();
        when(cartRepo.findById(1L)).thenReturn(Optional.of(cart));
        Optional<Cart> foundCart = cartService.getCartById(1L);
        assertTrue(foundCart.isPresent());
        verify(cartRepo, times(1)).findById(1L);
    }

    @Test
    public void testSaveOrUpdateCart() {
        Cart cart = new Cart();
        when(cartRepo.save(cart)).thenReturn(cart);
        Cart savedCart = cartService.saveOrUpdateCart(cart);
        assertNotNull(savedCart);
        verify(cartRepo, times(1)).save(cart);
    }

    @Test
    public void testDeleteCart() {
        when(cartRepo.existsById(1L)).thenReturn(true);
        cartService.deleteCart(1L);
        verify(cartRepo, times(1)).deleteById(1L);
    }
}
