package com.Vcriate.vcriateProject.UserControllerTest;
import com.Vcriate.vcriateProject.Entity.User;
import com.Vcriate.vcriateProject.Service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WalletServiceTest {

    @Autowired
    private WalletService service;

    @Test
    public void testAddAndWithdraw() {
        User user = service.createUser("rahul");

        String addResponse = service.addMoney(user.getId(), 100.0);
        assertTrue(addResponse.contains("New Balance: 100.0"));

        String withdrawResponse = service.withdrawMoney(user.getId(), 50.0);
        assertTrue(withdrawResponse.contains("New Balance: 50.0"));
    }

    @Test
    public void testWithdrawFailure() {
        User user = service.createUser("sasank");
        String response = service.withdrawMoney(user.getId(), 200.0);
        assertEquals("Insufficient balance!", response);
    }
}
