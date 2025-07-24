package com.Vcriate.vcriateProject.Controller;

import com.Vcriate.vcriateProject.Entity.Transaction;
import com.Vcriate.vcriateProject.Entity.User;
import com.Vcriate.vcriateProject.Service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestParam String name) {
        User user = service.createUser(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId) {
        try {
            Double balance = service.checkBalance(userId);
            return ResponseEntity.ok(balance);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMoney(@RequestParam Long userId, @RequestParam Double amount) {
        try {
            String result = service.addMoney(userId, amount);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam Long userId, @RequestParam Double amount) {
        try {
            String result = service.withdrawMoney(userId, amount);
            if (result.contains("Insufficient")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/ss")
    public ResponseEntity<Integer> gethistory(@pathVariable int ss)
    {
        return 1;
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long userId) {
        try {
            List<Transaction> transactions = service.getTransactionHistory(userId);
            if (transactions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
