package com.example.project_1.repository;

import com.example.project_1.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        Account account1 = new Account(null, "user1", "pass1", Account.Role.EMPLOYEE);
        Account account2 = new Account(null, "user2", "pass2", Account.Role.MANAGER);

        accountRepository.save(account1);
        accountRepository.save(account2);
    }

    @Test
    void testFindByUsername() {
        // Act
        Optional<Account> account = accountRepository.findByUsername("user1");

        // Assert
        assertTrue(account.isPresent());
        assertEquals("user1", account.get().getUsername());
    }

    @Test
    void testFindByUsernameNotFound() {
        // Act
        Optional<Account> account = accountRepository.findByUsername("nonexistent");

        // Assert
        assertFalse(account.isPresent());
    }

    @Test
    void testFindByAccountId() {
        // Arrange
        Account savedAccount = accountRepository.save(new Account(null, "newUser", "newPass", Account.Role.EMPLOYEE));

        // Act
        Optional<Account> account = accountRepository.findByAccountId(savedAccount.getAccountId());

        // Assert
        assertTrue(account.isPresent());
        assertEquals(savedAccount.getAccountId(), account.get().getAccountId());
    }
}
