package com.asudak.pico.nbi.server.security;

import com.asudak.pico.nbi.security.SecurityService;
import com.asudak.pico.nbi.server.security.model.GenerateTokenRequest;
import com.asudak.pico.nbi.server.security.model.RefreshTokenRequest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationEndpoint {

    private final SecurityService securityService;

    @Inject
    public AuthenticationEndpoint(SecurityService securityService) {
        this.securityService = securityService;
    }

    @POST
    public Response generateToken(@Valid GenerateTokenRequest generateTokenRequest) {
        securityService.generateToken(generateTokenRequest).toEither();
        return Response.noContent().build();
    }

    @POST
    @Path("/refresh")
    public Response refreshToken(@Valid RefreshTokenRequest refreshTokenRequest) {
        return Response.noContent().build();
    }

    @POST
    @Path("/invalidate")
    public Response invalidate() {
        return Response.noContent().build();
    }
}
