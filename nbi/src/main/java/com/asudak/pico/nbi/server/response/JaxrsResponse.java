package com.asudak.pico.nbi.server.response;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

public class JaxrsResponse {

    private final static String NOT_FOUND_MESSAGE = "Object with id '%s' not found.";
    private final static String NO_CONTENT_MESSAGE = "No content.";
    private final static String INTERNAL_ERROR_MESSAGE = "Error occurred during request processing: %s";

    public static <T> Response ok(T value) {
        return Response.ok(RichResponse.success(value)).build();
    }

    public static Response notFound(String id) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(RichResponse.error(String.format(NOT_FOUND_MESSAGE, id)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response noContent() {
        return Response.noContent()
                .entity(RichResponse.warning(NO_CONTENT_MESSAGE))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response serverError(String message) {
        return Response.serverError()
                .entity(RichResponse.error(String.format(INTERNAL_ERROR_MESSAGE, message)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response serverError(Exception e) {
        return Response.serverError()
                .entity(RichResponse.error(String.format(INTERNAL_ERROR_MESSAGE, e.getMessage())))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response constraintViolation(Collection<ValidationResponse.Violation> violations) {
        return Response.serverError()
                .entity(RichResponse.constraintViolation(violations))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
