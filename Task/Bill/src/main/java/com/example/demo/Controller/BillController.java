package com.example.demo.Controller;

import com.example.demo.Model.Bill;
import com.example.demo.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public Bill saveBill(@RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    @GetMapping("/{billId}")
    public Bill getBillById(@PathVariable String billId) {
        return billService.getBillById(billId);
    }

    @GetMapping("/users/{userId}")
    public List<Bill> getBillsByUserId(@PathVariable String userId) {
        return billService.getBillsByUserId(userId);
    }
}

