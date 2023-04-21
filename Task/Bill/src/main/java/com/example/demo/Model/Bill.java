package com.example.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bill")
public class Bill {
    private String billId;
    private String orderId;
    private String userId;
    private double totalAmount;
    private String status;


}

