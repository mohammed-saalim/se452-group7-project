package edu.depaul.cdm.se452.groupProject.payments;

import jakarta.persistence.*;
import edu.depaul.cdm.se452.groupProject.orders.CustomerOrder;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod;
    private double amount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // Ensures a valid CustomerOrder reference
    private CustomerOrder order;
}
