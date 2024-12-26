// package com.example.project_1.service;

// import com.example.project_1.entity.Account;
// import com.example.project_1.repository.AccountRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;

// @Service
// public class AccountService {

//     private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

//     @Autowired
//     private AccountRepository accountRepository;

//     /**
//      * Retrieve all accounts.
//      *
//      * @return a list of all accounts
//      */
//     public List<Account> getAllAccounts() {
//         return accountRepository.findAll();
//     }

//     /**
//      * Check if a user exists by account ID.
//      *
//      * @param accountId the ID of the account
//      * @return true if the account exists, false otherwise
//      */
//     public boolean userExists(Integer accountId) {
//         return accountRepository.findById(accountId).isPresent();
//     }

//     /**
//      * Check if a username already exists.
//      *
//      * @param username the username to check
//      * @return true if the username exists, false otherwise
//      */
//     public boolean usernameExists(String username) {
//         return accountRepository.findByUsername(username).isPresent();
//     }

//     /**
//      * Create a new account.
//      *
//      * @param newAccount the account object to be created
//      * @return the created account
//      */
//     public Account createAccount(Account newAccount) {
//         return accountRepository.save(newAccount);
//     }

//     /**
//      * Retrieve an account by its ID.
//      *
//      * @param accountId the ID of the account
//      * @return the account if found
//      * @throws RuntimeException if the account is not found
//      */
//     public Account getAccountById(Integer accountId) {
//         return accountRepository.findById(accountId)
//                 .orElseThrow(() -> new RuntimeException("Account with ID " + accountId + " not found"));
//     }

//     /**
//      * Perform user login by validating username and password.
//      *
//      * @param username the username of the account
//      * @param password the password of the account
//      * @return the account if login is successful
//      * @throws Exception if login fails
//      */
//     public Account login(String username, String password) throws Exception {
//         Optional<Account> accountOpt = accountRepository.findByUsername(username);

//         if (accountOpt.isPresent()) {
//             Account account = accountOpt.get();
//             if (account.getPassword().equals(password)) {
//                 return account;  // Login successful
//             } else {
//                 throw new Exception("Invalid password");
//             }
//         } else {
//             throw new Exception("Username not found");
//         }
//     }
// }

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
        logger.info("Fetching all accounts...");
        List<Account> accounts = accountRepository.findAll();
        logger.debug("Fetched accounts: {}", accounts);
        return accounts;
    }

    public boolean usernameExists(String username) {
        logger.info("Checking if username exists: {}", username);
        boolean exists = accountRepository.findByUsername(username).isPresent();
        logger.debug("Username '{}' exists: {}", username, exists);
        return exists;
    }

    public Account createAccount(Account newAccount) {
        logger.info("Creating a new account for username: {}", newAccount.getUsername());
        Account account = accountRepository.save(newAccount);
        logger.info("Account created successfully: {}", account);
        return account;
    }

    public Account login(String username, String password) throws Exception {
        logger.info("Attempting login for username: {}", username);
        Optional<Account> accountOpt = accountRepository.findByUsername(username);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getPassword().equals(password)) {
                logger.info("Login successful for username: {}", username);
                return account;
            } else {
                logger.warn("Invalid password for username: {}", username);
                throw new Exception("Invalid password");
            }
        } else {
            logger.warn("Username not found: {}", username);
            throw new Exception("Username not found");
        }
    }
}