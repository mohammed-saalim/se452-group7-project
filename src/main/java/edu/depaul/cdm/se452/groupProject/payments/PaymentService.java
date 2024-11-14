package edu.depaul.cdm.se452.groupProject.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        logger.info("Entering findAll() method");
        List<Payment> payments = paymentRepository.findAll();
        logger.info("Found {} payments", payments.size());
        return payments;
    }

    public Payment save(Payment payment) {
        logger.info("Entering save() method with payment: {}", payment);
        Payment savedPayment = paymentRepository.save(payment);
        logger.info("Saved payment with id: {}", savedPayment.getId());
        return savedPayment;
    }

    public Payment findById(Long id) {
        logger.info("Entering findById() method with id: {}", id);
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            logger.info("Found payment with id: {}", id);
        } else {
            logger.warn("Payment with id {} not found", id);
        }
        return payment;
    }
}
