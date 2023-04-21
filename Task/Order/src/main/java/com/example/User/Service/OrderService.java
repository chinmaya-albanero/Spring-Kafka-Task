package com.example.User.Service;

import com.example.User.Respo.OrderRepository;
import com.example.User.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order placeOrder(String order) {
        Order order = orderRepository.findById(orderId).orElse(null);

        // Return null if the order is not found
        if (order == null) {
            return null;
        }

        // Wait for the bill to be generated
        Bill bill = kafkaConsumer.receive("bill-topic");

        // Set the bill details in the order
        order.setTotalAmount(bill.getTotalAmount());
        order.setBillId(bill.getBillId());

        order.setStatus(String.valueOf(Order.Status.PLACED));
        return orderRepository.save(order);
    }


    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }


    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }


}


