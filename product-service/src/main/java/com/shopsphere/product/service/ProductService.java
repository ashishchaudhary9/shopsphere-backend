package com.shopsphere.product.service;

import com.shopsphere.product.document.Product;
import com.shopsphere.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(@PathVariable String id);

    public Product addProduct(Product product);

    public List<Product> addProducts(List<Product> products);

    public Product updateExistingProduct(String id, Product updatedProduct);

    public void deleteProductById(String id);

}
