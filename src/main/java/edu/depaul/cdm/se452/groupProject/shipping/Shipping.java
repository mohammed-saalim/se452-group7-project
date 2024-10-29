package edu.depaul.cdm.se452.groupProject.shipping;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Shipping { //Model
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String trackingNumber;

    @Column(name = "order_id")
    private Long orderId;
}
