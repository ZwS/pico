package com.asudak.pico.nbi.server.error;

import com.asudak.pico.nbi.server.response.JaxrsResponse;
import com.asudak.pico.nbi.server.response.ValidationResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.stream.Collectors;

@Produces
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return JaxrsResponse.constraintViolation(exception.getConstraintViolations().stream()
                .map(violation -> ValidationResponse.Violation.of(
                        String.valueOf(violation.getPropertyPath()),
                        violation.getMessage()
                )).collect(Collectors.toList())
        );
    }
}
