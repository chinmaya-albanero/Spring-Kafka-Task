package com.example.User.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")

public class Order {

    private  String id;
    private  String userId;
    private List<Product> products;
    private String status;

    public enum Status {
        PLACED,
        PENDING,
        REJECTED
    }
}