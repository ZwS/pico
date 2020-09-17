package com.asudak.pico.nbi.client.db.token;

import com.asudak.pico.db.service.security.TokenService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface TokenServiceClient extends TokenService {
}
