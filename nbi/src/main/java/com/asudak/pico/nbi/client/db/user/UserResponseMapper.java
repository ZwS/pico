package com.asudak.pico.nbi.client.db.user;

import com.asudak.pico.nbi.client.exception.ClientException;
import com.asudak.pico.nbi.client.exception.NotFoundClientException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class UserResponseMapper implements ResponseExceptionMapper<ClientException> {

    @Override
    public ClientException toThrowable(Response response) {
        switch (response.getStatus()) {
            case 404: return new NotFoundClientException();
        }

        return null;
    }
}
