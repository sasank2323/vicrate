package com.Vcriate.vcriateProject.Service;
import com.Vcriate.vcriateProject.Entity.Transaction;
import com.Vcriate.vcriateProject.Entity.User;
import com.Vcriate.vcriateProject.Repository.TransactionRepository;
import com.Vcriate.vcriateProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository txRepo;

    public Double checkBalance(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return user.getBalance();
    }

    public String addMoney(Long userId, Double amount) {
        User user = userRepo.findById(userId).orElseThrow();
        user.setBalance(user.getBalance() + amount);
        userRepo.save(user);
        txRepo.save(new Transaction(userId, "CREDIT", amount));
        return "Money added. New Balance: " + user.getBalance();
    }

    public String withdrawMoney(Long userId, Double amount) {
        User user = userRepo.findById(userId).orElseThrow();
        if (user.getBalance() < amount) {
            return "Insufficient balance!";
        }
        user.setBalance(user.getBalance() - amount);
        userRepo.save(user);
        txRepo.save(new Transaction(userId, "DEBIT", amount));
        return "Money withdrawn. New Balance: " + user.getBalance();
    }

    public List<Transaction> getTransactionHistory(Long userId) {
        return txRepo.findByUserId(userId);
    }

    public User createUser(String name) {
        return userRepo.save(new User(name));
    }
}
