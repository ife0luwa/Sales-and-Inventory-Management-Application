package dev.ifeoluwa.sales_inventory_management_application.repository;

import dev.ifeoluwa.sales_inventory_management_application.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author on 09/03/2023
 * @project
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
