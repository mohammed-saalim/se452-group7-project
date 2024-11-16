package edu.depaul.cdm.se452.groupProject.paymentsTest;

import edu.depaul.cdm.se452.groupProject.payments.Payment;
import edu.depaul.cdm.se452.groupProject.payments.PaymentRepository;
import edu.depaul.cdm.se452.groupProject.payments.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    public void setUp() {
        paymentRepository.deleteAll();
        payment = new Payment();
        payment.setId(1L);
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setAmount(100.00);
        payment.setStatus("SUCCESS");
    }

    @Test
    public void testFindAll() {
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment));

        List<Payment> payments = paymentService.findAll();

        assertNotNull(payments);
        assertEquals(1, payments.size());
        assertEquals("CREDIT_CARD", payments.get(0).getPaymentMethod());
    }

    @Test
    public void testSave() {
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.save(payment);

        assertNotNull(savedPayment);
        assertEquals(1L, savedPayment.getId());
        assertEquals("CREDIT_CARD", savedPayment.getPaymentMethod());
    }

    @Test
    public void testFindByIdPaymentFound() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentService.findById(1L);

        assertNotNull(foundPayment);
        assertEquals(1L, foundPayment.getId());
        assertEquals("CREDIT_CARD", foundPayment.getPaymentMethod());
    }

    @Test
    public void testFindByIdPaymentNotFound() {
        when(paymentRepository.findById(99L)).thenReturn(Optional.empty());

        Payment foundPayment = paymentService.findById(99L);

        assertNull(foundPayment);
    }
}