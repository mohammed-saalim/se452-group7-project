package edu.depaul.cdm.se452.groupProject.paymentsTest;

import edu.depaul.cdm.se452.groupProject.payments.Payment;
import edu.depaul.cdm.se452.groupProject.payments.PaymentRepository;
import edu.depaul.cdm.se452.groupProject.payments.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    private Payment payment;

    @BeforeEach
    void setUp() {
        paymentRepository.deleteAll();
        payment = new Payment();
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(100.0);
        payment.setStatus("Completed");
        payment.setOrder(null);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment);

        List<Payment> result = paymentService.findAll();

        assertEquals(1, result.size());
        assertEquals(payment, result.get(0));
    }

    @Test
    void testSave() {
        Payment result = paymentService.save(payment);
        assertEquals(payment, result);
        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindById() {
        Payment savedPayment = paymentRepository.save(payment);
        Payment result = paymentService.findById(savedPayment.getId());
        assertEquals(savedPayment, result);
    }
}