package com.asudak.pico.nbi.server.security;

import com.asudak.pico.db.model.ImmutableRoleDTO;
import com.asudak.pico.db.model.ImmutableUserDetailsDTO;
import com.asudak.pico.db.model.UserDetailsDTO;
import com.asudak.pico.db.service.user.UserService;
import com.asudak.pico.nbi.server.response.ObjectResponse;
import com.asudak.pico.nbi.server.security.model.GenerateTokenRequest;
import com.asudak.pico.nbi.server.security.model.TokenResponse;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestHTTPEndpoint(AuthenticationEndpoint.class)
public class AuthenticationEndpointTest {

    @InjectMock
    @RestClient
    UserService userService;

    @BeforeAll
    public static void setUp() {
        RestAssured.config = RestAssured.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().defaultObjectMapperType(ObjectMapperType.JSONB));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testGenerateToken() {
        GenerateTokenRequest tokenRequest = new GenerateTokenRequest("testUser", "Temp1234%");
        UserDetailsDTO userDetails = ImmutableUserDetailsDTO.builder()
                .id(UUID.randomUUID())
                .username(tokenRequest.getUsername())
                .addRoles(ImmutableRoleDTO.builder().name("user").build())
                .build();
        when(userService.getUser(tokenRequest.getUsername(), tokenRequest.getPassword())).thenReturn(userDetails);

        ObjectResponse<TokenResponse> response = given()
                .when()
                        .contentType(ContentType.JSON)
                        .body(tokenRequest)
                        .post()
                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().as(new TypeRef<>() {});

        assertNotNull(response.getData().getToken(), "Token should present in response");

        given().headers("Authorization", "Bearer " + response.getData().getToken())
                .when()
                    .get("/invalidate")
                .then()
                    .statusCode(is(not(401)));
    }
}