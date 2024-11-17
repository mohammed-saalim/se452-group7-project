package edu.depaul.cdm.se452.groupProject.payments;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.depaul.cdm.se452.groupProject.orders.CustomerOrder;
import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    @JsonBackReference
    private CustomerOrder order;
}
