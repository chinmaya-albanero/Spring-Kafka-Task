package com.example.User.controller;

import com.example.User.Service.OrderService;
import com.example.User.model.Order;
import com.example.User.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.User.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
//    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String orderTopic = "order-topic";

    public OrderController(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        // Send the order to the Kafka topic
        kafkaTemplate.send(orderTopic, order.getId(), order);
        Order placedOrder = orderService.placeOrder(order.getId());
        return ResponseEntity.ok(placedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(order);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
    // Calculate the total amount of a product
    public double calculateProductTotal(double price, int quantity) {
        return price * quantity;
    }

    // Calculate the total amount of the order
    private double calculateOrderTotal(Order order) {
        double total = 0.0;
        for (Product  product : order.getProducts()) {
            double productTotal = calculateProductTotal(product.getPrice(), product.getQuantity());
            total += productTotal;
        }
        return total;
    }

}

