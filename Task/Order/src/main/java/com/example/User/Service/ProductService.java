package com.example.User.Service;


import com.example.User.Respo.ProductRepository;
import com.example.User.Respo.UserRepository;
import com.example.User.model.Product;
import com.example.User.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.example.demo.exception.UserAlreadyExistsException;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    public  Product getProductById(String id){

        return  productRepository.findById(id).get();
    }
}