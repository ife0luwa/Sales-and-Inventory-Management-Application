package dev.ifeoluwa.sales_inventory_management_application.controller;

import dev.ifeoluwa.sales_inventory_management_application.dto.OrderDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Orders;
import dev.ifeoluwa.sales_inventory_management_application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author on 09/03/2023
 * @project
 */

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/create/{id}")
    public ResponseEntity<Orders> createOrder(@PathVariable("id") Long productId, @RequestBody OrderDTO orderDTO) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(orderService.createOrder(orderDTO, productId), HttpStatus.CREATED);
    }
}
