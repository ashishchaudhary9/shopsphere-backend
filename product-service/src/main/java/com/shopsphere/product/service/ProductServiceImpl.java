package com.shopsphere.product.service;

import com.shopsphere.product.document.Product;
import com.shopsphere.product.exception.ProductNotFoundException;
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
public class ProductServiceImpl implements ProductService{

    public final ProductRepository repository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductServiceImpl(ProductRepository repository){
        this.repository=repository;
    }

    public List<Product> getAllProducts(){
        List<Product> listOfProducts = repository.findAll();
        log.info("We have {} products available", listOfProducts.size());
        return listOfProducts;
    }
    public Product getProductById(@PathVariable String id){
        return repository.findById(id).orElseThrow(
                ()-> new ProductNotFoundException("Error in finding product by id : "+ id));
    }

    public Product addProduct(Product product){
        return repository.save(product);

    }

    public List<Product> addProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public Product updateExistingProduct(String id, Product updatedProduct){
        Product updateProduct = repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("error in updating existing product for id : "+ id ));

        updateProduct.setName(updatedProduct.getName());
        updateProduct.setPrice(updatedProduct.getPrice());
        updateProduct.setStock(updatedProduct.getStock());
        updateProduct.setDescription(updatedProduct.getDescription());
        updateProduct.setCategoryId(updatedProduct.getCategoryId());
        return repository.save(updateProduct);

    }

    public void deleteProductById(String id){
        repository.deleteById(id);

    }

}
