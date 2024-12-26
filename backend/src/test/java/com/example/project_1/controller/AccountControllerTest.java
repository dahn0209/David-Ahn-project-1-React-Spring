// package com.example.project_1.controller;

// import com.example.project_1.entity.Account;
// import com.example.project_1.service.AccountService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(AccountController.class)
// class AccountControllerTest {

//     private static final Logger logger = LoggerFactory.getLogger(AccountControllerTest.class);

//     @Autowired
//     private MockMvc mockMvc;

//     @Mock
//     private AccountService accountService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         logger.debug("Mocks initialized for AccountControllerTest.");
//     }

//     @Test
//     void testGetAllAccounts() throws Exception {
//         logger.info("Starting test: testGetAllAccounts");
//         List<Account> accounts = List.of(new Account(1, "user1", "password1", Account.Role.EMPLOYEE));
//         when(accountService.getAllAccounts()).thenReturn(accounts);

//         mockMvc.perform(get("/accounts"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].username").value("user1"));

//         verify(accountService, times(1)).getAllAccounts();
//         logger.info("Completed test: testGetAllAccounts");
//     }

//     @Test
//     void testRegisterAccount_Success() throws Exception {
//         logger.info("Starting test: testRegisterAccount_Success");
//         Account account = new Account(1, "newUser", "newPassword", Account.Role.EMPLOYEE);
//         when(accountService.createAccount(any(Account.class))).thenReturn(account);

//         mockMvc.perform(post("/accounts/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("{\"username\":\"newUser\", \"password\":\"newPassword\"}"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.username").value("newUser"));

//         verify(accountService, times(1)).createAccount(any(Account.class));
//         logger.info("Completed test: testRegisterAccount_Success");
//     }

//     @Test
//     void testLoginAccount_Success() throws Exception {
//         logger.info("Starting test: testLoginAccount_Success");
//         Account account = new Account(1, "validUser", "validPassword", Account.Role.EMPLOYEE);
//         when(accountService.login("validUser", "validPassword")).thenReturn(account);

//         mockMvc.perform(post("/accounts/login")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("{\"username\":\"validUser\", \"password\":\"validPassword\"}"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.username").value("validUser"));

//         verify(accountService, times(1)).login("validUser", "validPassword");
//         logger.info("Completed test: testLoginAccount_Success");
//     }
// }
