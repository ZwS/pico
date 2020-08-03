package com.asudak.pico.nbi.server.response;

import java.util.Collection;
import java.util.List;

public abstract class RichResponse {

    private final Status status;

    protected RichResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public static RichResponse success(String message) {
        return new MessageResponse(Status.SUCCESS, message);
    }

    public static RichResponse success(Object data) {
        return new ObjectResponse(Status.SUCCESS, data);
    }

    public static RichResponse warning(String message) {
        return new MessageResponse(Status.WARNING, message);
    }

    public static RichResponse error(String message) {
        return new MessageResponse(Status.ERROR, message);
    }

    public static RichResponse constraintViolation(List<ValidationResponse.Violation> violations) {
        return new ValidationResponse(violations);
    }

    protected enum Status {
        SUCCESS,
        WARNING,
        ERROR
    }
}
