package com.example.project_1.service;

import com.example.project_1.entity.Account;
import com.example.project_1.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAccounts() {
        // Arrange
        Account account1 = new Account(1, "user1", "pass1", Account.Role.EMPLOYEE);
        Account account2 = new Account(2, "user2", "pass2", Account.Role.MANAGER);
        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));

        // Act
        var accounts = accountService.getAllAccounts();

        // Assert
        assertEquals(2, accounts.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testUsernameExists() {
        // Arrange
        String username = "existingUser";
        when(accountRepository.findByUsername(username)).thenReturn(Optional.of(new Account()));

        // Act
        boolean exists = accountService.usernameExists(username);

        // Assert
        assertTrue(exists);
        verify(accountRepository, times(1)).findByUsername(username);
    }

    @Test
    void testCreateAccount() {
        // Arrange
        Account newAccount = new Account(null, "newUser", "newPass", Account.Role.EMPLOYEE);
        when(accountRepository.save(newAccount)).thenReturn(new Account(1, "newUser", "newPass", Account.Role.EMPLOYEE));

        // Act
        Account createdAccount = accountService.createAccount(newAccount);

        // Assert
        assertNotNull(createdAccount.getAccountId());
        assertEquals("newUser", createdAccount.getUsername());
        verify(accountRepository, times(1)).save(newAccount);
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Arrange
        String username = "user1";
        String password = "pass1";
        Account account = new Account(1, username, password, Account.Role.EMPLOYEE);
        when(accountRepository.findByUsername(username)).thenReturn(Optional.of(account));

        // Act
        Account loggedInAccount = accountService.login(username, password);

        // Assert
        assertNotNull(loggedInAccount);
        assertEquals(username, loggedInAccount.getUsername());
        verify(accountRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoginInvalidPassword() {
        // Arrange
        String username = "user1";
        String password = "wrongPass";
        Account account = new Account(1, username, "correctPass", Account.Role.EMPLOYEE);
        when(accountRepository.findByUsername(username)).thenReturn(Optional.of(account));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> accountService.login(username, password));
        assertEquals("Invalid password", exception.getMessage());
        verify(accountRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoginUserNotFound() {
        // Arrange
        String username = "nonexistentUser";
        when(accountRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> accountService.login(username, "password"));
        assertEquals("Username not found", exception.getMessage());
        verify(accountRepository, times(1)).findByUsername(username);
    }
}
