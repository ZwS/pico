package com.asudak.pico.nbi.client.db.user;

import com.asudak.pico.db.service.user.UserService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface UserServiceClient extends UserService {
}
