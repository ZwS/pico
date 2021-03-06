package com.asudak.pico.nbi.server.security;

import com.asudak.pico.db.service.user.UserService;
import com.asudak.pico.nbi.server.response.JaxrsResponse;
import com.asudak.pico.nbi.server.security.model.GenerateTokenRequest;
import com.asudak.pico.nbi.server.security.model.TokenResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.asudak.pico.nbi.client.ClientHelper.handlingNotFound;

@RequestScoped
@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationEndpoint {

    @Inject
    JwtService jwtService;
    @RestClient
    UserService userService;

    @POST
    @PermitAll
    public Response generateToken(@Valid GenerateTokenRequest request) {
        return handlingNotFound(() -> userService.getUser(request.getUsername(), request.getPassword()))
                .map(jwtService::generateToken)
                .map(TokenResponse::new)
                .map(JaxrsResponse::ok)
                .orElseGet(JaxrsResponse::unauthorized);
    }
}
