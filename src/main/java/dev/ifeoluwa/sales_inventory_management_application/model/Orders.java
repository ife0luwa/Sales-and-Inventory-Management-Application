package dev.ifeoluwa.sales_inventory_management_application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author on 09/03/2023
 * @project
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID orderId;
    private Long productId;
    private int quantity;
    private Double totalPrice;
    @ManyToOne
    private Customer customer;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
