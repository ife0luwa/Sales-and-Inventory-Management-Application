package dev.ifeoluwa.sales_inventory_management_application.controller;

import dev.ifeoluwa.sales_inventory_management_application.dto.ProductDTO;
import dev.ifeoluwa.sales_inventory_management_application.model.Product;
import dev.ifeoluwa.sales_inventory_management_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author on 09/03/2023
 * @project
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }


    @GetMapping("/fetch-all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


    @PatchMapping("/price/change/{id}")
    public ResponseEntity<Product> changeProductPrice(@PathVariable Long id, @RequestParam double newPrice) throws ChangeSetPersister.NotFoundException {
        return  new ResponseEntity<>(productService.changeProductPrice(id, newPrice), HttpStatus.ACCEPTED);
    }


}
