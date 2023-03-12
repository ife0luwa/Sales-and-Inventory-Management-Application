package dev.ifeoluwa.sales_inventory_management_application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.ifeoluwa.sales_inventory_management_application.Exception.BadRequestException;
import dev.ifeoluwa.sales_inventory_management_application.dto.OrderDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Customer;
import dev.ifeoluwa.sales_inventory_management_application.model.Orders;
import dev.ifeoluwa.sales_inventory_management_application.model.Product;
import dev.ifeoluwa.sales_inventory_management_application.repository.OrderRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private CustomerService customerService;

    @MockBean
    private KafkaProducer kafkaProducer;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private ProductService productService;


    @Test
    void testCreateOrder() throws ChangeSetPersister.NotFoundException {
        Orders orders = new Orders();
        when(orderRepository.save((Orders) any())).thenReturn(orders);

        Product product = new Product();
        product.setPrice(10.0d);
        product.setQuantityInStock(1L);
        when(productService.updateProduct((Long) any(), (Product) any())).thenReturn(new Product());
        when(productService.getProductById((Long) any())).thenReturn(product);
        when(customerService.createCustomer((OrderDTO) any())).thenReturn(new Customer(1, "Name", "4105551212"));
        doNothing().when(kafkaProducer).sendMessage((Orders) any());
        assertSame(orders, orderService.createOrder(new OrderDTO(1, "Customer Name", "4105551212"), 123L));
        verify(orderRepository).save((Orders) any());
        verify(productService).getProductById((Long) any());
        verify(productService).updateProduct((Long) any(), (Product) any());
        verify(customerService).createCustomer((OrderDTO) any());
        verify(kafkaProducer).sendMessage((Orders) any());
    }


    @Test
    void testFillOrder() {
        Orders actualFillOrderResult = orderService.fillOrder(new OrderDTO(1, "Customer Name", "4105551212"));
        assertEquals(1, actualFillOrderResult.getQuantity());
        assertNull(actualFillOrderResult.getProductId());
    }


}

