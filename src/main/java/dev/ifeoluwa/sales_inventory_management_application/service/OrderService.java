package dev.ifeoluwa.sales_inventory_management_application.service;

import dev.ifeoluwa.sales_inventory_management_application.Exception.BadRequestException;
import dev.ifeoluwa.sales_inventory_management_application.dto.OrderDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Customer;
import dev.ifeoluwa.sales_inventory_management_application.model.Orders;
import dev.ifeoluwa.sales_inventory_management_application.model.Product;
import dev.ifeoluwa.sales_inventory_management_application.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author on 09/03/2023
 * @project
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;

    private CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }



//    public Order createOrder(Order order, CustomerDTO customerDTO) throws ChangeSetPersister.NotFoundException {
//        Product product = productService.getProductById(order.getProductId());
//
//        if(product != null && product.getQuantityInStock() >= order.getQuantity()) {
//            product.setQuantityInStock(product.getQuantityInStock() - order.getQuantity());
//            productService.updateProduct(product.getId(), product);
//            order.setTotalPrice(order.getQuantity() * product.getPrice());
//            Customer customer = customerService.createCustomer(customerDTO);
//            order.setCustomer(customer);
//            return orderRepository.save(order);
//        } else {
//            throw new BadRequestException("Product not available");
//        }
//    }

    public Orders createOrder(OrderDTO orderDTO, Long productId) throws ChangeSetPersister.NotFoundException {
        Product product = productService.getProductById(productId);

        if(product != null && product.getQuantityInStock() >= orderDTO.getQuantity()) {
            Orders orders = fillOrder(orderDTO);

            product.setQuantityInStock(product.getQuantityInStock() - orderDTO.getQuantity());
            productService.updateProduct(product.getId(), product);
            orders.setTotalPrice(orderDTO.getQuantity() * product.getPrice());
            Customer customer = customerService.createCustomer(orderDTO);
            orders.setProductId(productId);
            orders.setCustomer(customer);
            return orderRepository.save(orders);
        } else {
            throw new BadRequestException("Product not available");
        }
    }


    public Orders fillOrder(OrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setProductId(orders.getProductId());
        orders.setQuantity(orderDTO.getQuantity());
        orders.setOrderId(UUID.randomUUID());
        return orders;
    }




}
