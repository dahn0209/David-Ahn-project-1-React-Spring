// package com.example.project_1.service;

// import com.example.project_1.entity.Account;
// import com.example.project_1.repository.AccountRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// class AccountServiceTest {

//     @Mock
//     private AccountRepository accountRepository;

//     @InjectMocks
//     private AccountService accountService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testGetAllAccounts() {
//         // Arrange
//         when(accountRepository.findAll()).thenReturn(
//             List.of(new Account(1, "user1", "password1", Account.Role.EMPLOYEE),
//                     new Account(2, "user2", "password2", Account.Role.MANAGER))
//         );

//         // Act
//         var accounts = accountService.getAllAccounts();

//         // Assert
//         assertEquals(2, accounts.size());
//         verify(accountRepository, times(1)).findAll();
//     }

//     @Test
//     void testCreateAccount() {
//         // Arrange
//         Account account = new Account(1, "newUser", "password123", Account.Role.EMPLOYEE);
//         when(accountRepository.save(any(Account.class))).thenReturn(account);

//         // Act
//         Account createdAccount = accountService.createAccount(account);

//         // Assert
//         assertNotNull(createdAccount);
//         assertEquals("newUser", createdAccount.getUsername());
//         verify(accountRepository, times(1)).save(account);
//     }

//     @Test
//     void testLoginSuccess() throws Exception {
//         // Arrange
//         Account account = new Account(1, "testUser", "password123", Account.Role.EMPLOYEE);
//         when(accountRepository.findByUsername("testUser")).thenReturn(Optional.of(account));

//         // Act
//         Account loggedInAccount = accountService.login("testUser", "password123");

//         // Assert
//         assertNotNull(loggedInAccount);
//         assertEquals("testUser", loggedInAccount.getUsername());
//         verify(accountRepository, times(1)).findByUsername("testUser");
//     }

//     @Test
//     void testLoginFailureInvalidPassword() {
//         // Arrange
//         Account account = new Account(1, "testUser", "password123", Account.Role.EMPLOYEE);
//         when(accountRepository.findByUsername("testUser")).thenReturn(Optional.of(account));

//         // Act & Assert
//         assertThrows(Exception.class, () -> accountService.login("testUser", "wrongPassword"));
//         verify(accountRepository, times(1)).findByUsername("testUser");
//     }

//     @Test
//     void testLoginFailureUsernameNotFound() {
//         // Arrange
//         when(accountRepository.findByUsername("unknownUser")).thenReturn(Optional.empty());

//         // Act & Assert
//         assertThrows(Exception.class, () -> accountService.login("unknownUser", "password"));
//         verify(accountRepository, times(1)).findByUsername("unknownUser");
//     }
// }

