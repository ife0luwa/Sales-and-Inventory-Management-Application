package dev.ifeoluwa.sales_inventory_management_application.repository;

import dev.ifeoluwa.sales_inventory_management_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author on 09/03/2023
 * @project
 */
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
