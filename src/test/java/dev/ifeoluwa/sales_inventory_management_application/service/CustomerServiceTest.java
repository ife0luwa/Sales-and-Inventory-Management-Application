package dev.ifeoluwa.sales_inventory_management_application.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.ifeoluwa.sales_inventory_management_application.dto.OrderDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Customer;
import dev.ifeoluwa.sales_inventory_management_application.repository.CustomerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @Test
    void testCreateCustomer() {
        Customer customer = new Customer(1, "Name", "4105551212");

        when(customerRepository.save((Customer) any())).thenReturn(customer);
        assertSame(customer, customerService.createCustomer(new OrderDTO(1, "Customer Name", "4105551212")));
        verify(customerRepository).save((Customer) any());
    }

}

