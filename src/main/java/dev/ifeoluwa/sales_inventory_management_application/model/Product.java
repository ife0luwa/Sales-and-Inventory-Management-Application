package dev.ifeoluwa.sales_inventory_management_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author on 09/03/2023
 * @project
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID productId;
    private String name;
    private Double price;
    private Long quantityInStock;
    private String description;
    private Boolean isAvailable;


//    public Boolean isAvailable() {
//        if(quantityInStock <= 0) {
//            return false;
//        }
//        return true;
//    }
}
