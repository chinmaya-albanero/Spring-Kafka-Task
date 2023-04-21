package com.example.User.controller;

import com.example.User.Service.ProductService;
import com.example.User.model.Product;
import com.example.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/post")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product savedProduct = productService.addProduct(product);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct(){
        return  new ResponseEntity<List<Product>>(productService.getAllProduct(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public  ResponseEntity<Product> getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);
        return  new ResponseEntity<Product>(product ,HttpStatus.OK);
    }
}
