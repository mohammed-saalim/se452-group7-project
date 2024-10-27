package edu.depaul.cdm.se452.groupProject.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
