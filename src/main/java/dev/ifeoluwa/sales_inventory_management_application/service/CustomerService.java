package dev.ifeoluwa.sales_inventory_management_application.service;

import dev.ifeoluwa.sales_inventory_management_application.dto.OrderDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Customer;
import dev.ifeoluwa.sales_inventory_management_application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author on 09/03/2023
 * @project
 */
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(OrderDTO orderDTO) {
        Customer customer = new Customer();
        customer.setName(orderDTO.getCustomerName());
        customer.setPhoneNumber(orderDTO.getPhoneNumber());
        return customerRepository.save(customer);
    }
}
