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
public class CustomerDTO {
    private String name;
    private String phoneNumber;
}
