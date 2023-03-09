package dev.ifeoluwa.sales_inventory_management_application.service;

import dev.ifeoluwa.sales_inventory_management_application.dto.ProductDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Product;
import dev.ifeoluwa.sales_inventory_management_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author on 09/03/2023
 * @project
 */
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        if(product.getQuantityInStock() != null) {
            product.setQuantityInStock(product.getQuantityInStock() + productDTO.getQuantityInStock());
        } else{
            product.setQuantityInStock(productDTO.getQuantityInStock());
        }
        product.setIsAvailable(true);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantityInStock(product.getQuantityInStock());
            existingProduct.setIsAvailable(true);
            return productRepository.save(existingProduct);
        }
        return null;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) throws ChangeSetPersister.NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }


    public Product changeProductPrice(Long id, double newPrice) throws ChangeSetPersister.NotFoundException {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        existingProduct.setPrice(newPrice);
        return productRepository.save(existingProduct);
    }



}
