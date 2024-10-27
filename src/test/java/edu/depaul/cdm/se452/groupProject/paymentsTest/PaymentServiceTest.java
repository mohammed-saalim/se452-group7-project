package edu.depaul.cdm.se452.groupProject.paymentsTest;

import edu.depaul.cdm.se452.groupProject.payments.Payment;
import edu.depaul.cdm.se452.groupProject.payments.PaymentRepository;
import edu.depaul.cdm.se452.groupProject.payments.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        // Spring handles the context
    }
    @Test
    public void testProcessPayment() {
        Payment payment = new Payment();
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(30.0);
        payment.setStatus("Completed");

        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.processPayment(payment);

        assertEquals("Credit Card", savedPayment.getPaymentMethod());
        assertEquals(30.0, savedPayment.getAmount());
        assertEquals("Completed", savedPayment.getStatus());
    }
}

