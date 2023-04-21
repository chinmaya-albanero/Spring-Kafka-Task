package com.example.demo.Repository;

import com.example.demo.Model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {
    // Custom methods if needed
}
