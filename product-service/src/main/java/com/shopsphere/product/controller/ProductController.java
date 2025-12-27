package com.shopsphere.product.controller;

import com.shopsphere.product.document.Product;
import com.shopsphere.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    public final ProductService service;

    public ProductController(ProductService service){
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = service.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) throws Exception{
        Product product = service.getProductById(id);
        if(product==null){
            throw new Exception("Id not found");
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products){
        List<Product> savedProducts = service.addProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product){
        Product updateProduct = service.updateExistingProduct(id,product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable String id){
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
