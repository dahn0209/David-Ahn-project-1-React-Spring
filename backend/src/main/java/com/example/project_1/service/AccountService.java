package com.example.project_1.service;

import com.example.project_1.entity.Account;
import com.example.project_1.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public boolean usernameExists(String username) {
        boolean exists = accountRepository.findByUsername(username).isPresent();
        return exists;
    }

    public Account createAccount(Account newAccount) {
        Account account = accountRepository.save(newAccount);
        return account;
    }

    public Account login(String username, String password) throws Exception {
        Optional<Account> accountOpt = accountRepository.findByUsername(username);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getPassword().equals(password)) {
                return account;
            } else {
                throw new Exception("Invalid password");
            }
        } else {
            throw new Exception("Username not found");
        }
    }
}