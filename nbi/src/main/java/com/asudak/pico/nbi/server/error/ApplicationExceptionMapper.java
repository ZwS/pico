package com.asudak.pico.nbi.server.error;

import com.asudak.pico.nbi.exception.ApplicationException;
import com.asudak.pico.nbi.server.response.JaxrsResponse;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    private final Logger logger;

    @Inject
    public ApplicationExceptionMapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Response toResponse(ApplicationException exception) {
        logger.log(Level.SEVERE, "Error occurred during request processing.", exception);
        return JaxrsResponse.serverError(exception);
    }
}
