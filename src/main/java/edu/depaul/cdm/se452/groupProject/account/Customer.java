package edu.depaul.cdm.se452.groupProject.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

import edu.depaul.cdm.se452.groupProject.orders.Cart;
import edu.depaul.cdm.se452.groupProject.orders.CustomerOrder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;
    private String lastName;
    private String email;

    // Relationship with Account: One customer can have one account
    @OneToOne
    @JoinColumn(name = "account_id") // Foreign key in the Customer table
    private Account account; // A Customer belongs to one Account

    // Relationship with Cart: One customer can have multiple carts
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    // Relationship with CustomerOrder: One customer can have multiple orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerOrder> orders;
}
