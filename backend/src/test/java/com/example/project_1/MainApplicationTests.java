// package com.example.project_1;

// import org.junit.jupiter.api.Test;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.context.ApplicationContext;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// class MainApplicationTests {

//     private static final Logger logger = LoggerFactory.getLogger(MainApplicationTests.class);

//     @Autowired
//     private ApplicationContext applicationContext;

//     @Test
//     void contextLoads() {
//         logger.info("Starting contextLoads test.");
//         assertNotNull(applicationContext, "Application context should not be null.");
//         logger.info("Application context loaded successfully.");
//     }

//     @Test
//     void testBeanPresence() {
//         logger.info("Starting testBeanPresence.");
//         assertTrue(applicationContext.containsBean("accountService"), "AccountService bean should be present.");
//         assertTrue(applicationContext.containsBean("ticketService"), "TicketService bean should be present.");
//         logger.info("All required beans are present.");
//     }

//     @Test
//     void testEnvironmentProperties() {
//         logger.info("Starting testEnvironmentProperties.");
//         String datasourceUrl = applicationContext.getEnvironment().getProperty("spring.datasource.url");
//         assertNotNull(datasourceUrl, "Datasource URL should be defined in application properties.");
//         logger.info("Datasource URL: {}", datasourceUrl);
//     }
// }
