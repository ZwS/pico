package com.asudak.pico.nbi.server.security;

import com.asudak.pico.db.model.ImmutableRoleDTO;
import com.asudak.pico.db.model.ImmutableUserDetailsDTO;
import com.asudak.pico.db.model.UserDetailsDTO;
import com.asudak.pico.db.service.user.UserService;
import com.asudak.pico.nbi.server.security.model.GenerateTokenRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@QuarkusTest
class AuthenticationEndpointTest {

    @InjectMock
    @RestClient
    UserService userService;

    @Test
    void testGenerateToken() {
        GenerateTokenRequest tokenRequest = new GenerateTokenRequest("testUser", "Temp1234%");
        UserDetailsDTO userDetails = ImmutableUserDetailsDTO.builder()
                .id(UUID.randomUUID())
                .username(tokenRequest.getUsername())
                .addRoles(ImmutableRoleDTO.builder().name("user").build())
                .build();
        when(userService.getUser(tokenRequest.getUsername(), tokenRequest.getPassword())).thenReturn(userDetails);

        JsonPath response = given()
                .when()
                        .contentType(ContentType.JSON)
                        .body(tokenRequest)
                        .post("/token")
                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath();

        assertNotNull(response.getString("data.token"), "Token should present in response");
    }
}