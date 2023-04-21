package com.example.User.model;






import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")

public class Product {

    private String id;
    private String name;
    private double price;
    private Integer quantity;

    public double getPrice() {
        return this.price= price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
