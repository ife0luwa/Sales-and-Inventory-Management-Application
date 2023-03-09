package dev.ifeoluwa.sales_inventory_management_application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author on 09/03/2023
 * @project
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private String name;
    private Double price;
    private Long quantityInStock;
    private String description;

}
