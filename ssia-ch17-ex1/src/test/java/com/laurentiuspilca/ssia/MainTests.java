package com.laurentiuspilca.ssia;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class MainTests {

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("""
        When calling the /hello endpoint without a user,
        the application should return HTTP 401 Unauthorized.""")
    void testCallHelloWithoutUser() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    @DisplayName("""
        When calling the /hello endpoint with a valid user,
        the application should return HTTP 200 OK.""")
    @WithUserDetails("john")
    void testCallHelloWithValidUser() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("""
        When calling the /hello endpoint with a mock user,
        the application should return HTTP 200 OK.""")
    @WithMockUser
    void testCallHelloWithMockUser() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("""
        When calling the /hello endpoint with a mock user,
        the application should return HTTP 200 OK.""")
    void testCallHelloWithValidUserWithMockUser() {
        client.mutateWith(mockUser())
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("""
        When calling the /hello POST endpoint with a mock user
        and a proper CSRF token, the application should return HTTP 200 OK.""")
    void testCallPostHelloWithValidUserWithoutUserWithCsrfToken() {
        client.mutateWith(csrf())
                .post()
                .uri("/hello")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    @DisplayName("""
        When calling the /hello POST endpoint with a mock user
        and a proper CSRF token, the application should return HTTP 200 OK.""")
    void testCallPostHelloWithValidUserWithMockUserWithCsrfToken() {
        client.mutateWith(mockUser())
                .mutateWith(csrf())
                .post()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }

}
