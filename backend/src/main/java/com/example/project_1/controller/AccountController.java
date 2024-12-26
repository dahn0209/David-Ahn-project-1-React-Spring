package com.example.project_1.controller;

import com.example.project_1.entity.Account;
import com.example.project_1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<Object> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        
        if (accounts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No accounts found.");
        }
        
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerAccount(@RequestBody Account newAccount) {
        if (newAccount.getUsername() == null || newAccount.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required.");
        }
        if (newAccount.getPassword() == null || newAccount.getPassword().length() < 4) {
            return ResponseEntity.badRequest().body("Password must be at least 4 characters.");
        }
        if (accountService.usernameExists(newAccount.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }
        Account account = accountService.createAccount(newAccount);
        return ResponseEntity.ok(account);
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> loginAccount(@RequestBody Account loginRequest) {
        try {
            Account account = accountService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
