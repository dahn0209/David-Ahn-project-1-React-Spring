// package com.example.project_1.config;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.reactive.server.WebTestClient;

// @SpringBootTest
// @AutoConfigureWebTestClient
// class CorsConfigTest {

//     @Autowired
//     private WebTestClient webTestClient;

//     @Test
//     void testCorsFilterAllowsAllOrigins() {
//         // Perform an OPTIONS request to test CORS behavior
//         webTestClient.options()
//                 .uri("/any-endpoint")
//                 .header("Origin", "http://example.com")
//                 .header("Access-Control-Request-Method", "GET")
//                 .exchange()
//                 .expectStatus().isOk()
//                 .expectHeader().valueEquals("Access-Control-Allow-Origin", "*")
//                 .expectHeader().valueEquals("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
//     }
// }
