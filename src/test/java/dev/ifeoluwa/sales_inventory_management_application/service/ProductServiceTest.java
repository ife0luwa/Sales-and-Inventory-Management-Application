package dev.ifeoluwa.sales_inventory_management_application.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.ifeoluwa.sales_inventory_management_application.model.Product;
import dev.ifeoluwa.sales_inventory_management_application.repository.ProductRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void testGetProductById() throws ChangeSetPersister.NotFoundException {
        Product product = new Product();
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(product));
        assertSame(product, productService.getProductById(123L));
        verify(productRepository).findById((Long) any());
    }

    @Test
    void testChangeProductPrice() throws ChangeSetPersister.NotFoundException {
        Product product = new Product();
        when(productRepository.save((Product) any())).thenReturn(product);
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertSame(product, productService.changeProductPrice(123L, 10.0d));
        verify(productRepository).save((Product) any());
        verify(productRepository).findById((Long) any());
    }

}

