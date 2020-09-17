package com.asudak.pico.db.service.user;

import com.asudak.pico.db.model.UserDTO;
import com.asudak.pico.db.model.UserDetailsDTO;
import com.asudak.pico.db.model.page.Page;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/user")
public interface UserService {

    @GET
    Page<UserDTO> getUsers(@QueryParam("page") Integer page);

    @GET
    @Path("/{id}")
    UserDTO getUser(@PathParam("id") String id);

    @GET
    @Path("/{id}/details")
    UserDetailsDTO getUserDetails(@PathParam("id") String id);

    @POST
    @Path("/authenticate")
    UserDetailsDTO getUser(String username, String passwordHash);

}
