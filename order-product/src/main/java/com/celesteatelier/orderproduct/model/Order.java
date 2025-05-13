package com.celesteatelier.orderproduct.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "orders") // 'order' is a reserved SQL keyword
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    private LocalDateTime orderDate;

    private Double totalAmount;


    private String orderStatus;

    // Store only user ID (User entity exists in another microservice)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    // Store only payment and shipping IDs (since Payment & Shipping are separate microservices)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "shipping_id")
    private Long shippingId;

    public void setOrderStatus(String orderStatus){
        this.orderStatus=orderStatus;
    }
}
