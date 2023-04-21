package com.example.demo.Service;

import com.example.demo.Model.Bill;
import com.example.demo.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;


    private final KafkaTemplate<String, Bill> kafkaTemplate;
    private final String billTopic = "bill-topic";

    public BillService(KafkaTemplate<String, Bill> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic")
    public void processOrder(Order order) {
        // Calculate the total amount of the order
        double totalAmount = calculateOrderTotal(order);
        // Create the bill
        Bill bill = new Bill();
        bill.setOrderId(order.getId());
        bill.setUserId(order.getUserId());
        bill.setTotalAmount(totalAmount);
        bill.setStatus("unpaid");
        // Send the bill to the Kafka topic
        kafkaTemplate.send(billTopic, bill.getBillId(), bill);
    }
    private double calculateProductTotal(double price, int quantity) {
        return price * quantity;
    }

    // Calculate the total amount of the order
    private double calculateOrderTotal(Order order) {
        double total = 0.0;
        for (String product : order.getProducts()) {
            double productTotal = calculateProductTotal(product.getPrice(), product.getQuantity());
            total += productTotal;
        }
        return total;
   }



    public Bill saveBill(Bill bill) {
        // Save the bill to the MongoDB database
        Bill savedBill = billRepository.save(bill);

        // Publish the bill to the Kafka topic
        kafkaTemplate.send(billTopic, savedBill);

        return savedBill;
    }

    public Bill getBillById(String billId) {
        return billRepository.findById(billId).orElse(null);
    }

    public List<Bill> getBillsByUserId(String userId) {
        return billRepository.findAll().stream()
                .filter(bill -> bill.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}

