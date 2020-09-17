package com.asudak.pico.nbi.server.user;

import com.asudak.pico.db.model.page.Page;
import com.asudak.pico.nbi.server.response.JaxrsResponse;
import com.asudak.pico.nbi.client.db.user.UserServiceClient;
import com.asudak.pico.nbi.server.user.model.CreateUserRequest;
import com.asudak.pico.nbi.server.user.model.UpdatePasswordRequest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import static com.asudak.pico.nbi.client.ClientHelper.handlingNotFound;
import static com.asudak.pico.nbi.client.ClientHelper.withoutHandlingErrors;
import static java.util.function.Predicate.not;

@Named
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {

    private final UserServiceClient userService;

    @Inject
    public UserEndpoint(@RestClient UserServiceClient userService) {
        this.userService = userService;
    }

    @GET
    public Response getUsers(@QueryParam("page") @DefaultValue("1") int page) {
        return withoutHandlingErrors(() -> userService.getUsers(page))
                .filter(not(Page::isEmpty))
                .map(JaxrsResponse::ok)
                .orElseGet(JaxrsResponse::noContent);

    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) {
        return handlingNotFound(() -> userService.getUser(id))
                .map(JaxrsResponse::ok)
                .orElseGet(() -> JaxrsResponse.notFound(id));
    }

    @POST
    public Response createUser(@Valid CreateUserRequest request) {
        return Response.notModified().build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id) {
        return Response.notModified().build();
    }

    @POST
    @Path("/{id}/password")
    public Response updateUserPassword(@PathParam("id") String id, @Valid UpdatePasswordRequest request) {
        return Response.notModified().build();
    }
}
