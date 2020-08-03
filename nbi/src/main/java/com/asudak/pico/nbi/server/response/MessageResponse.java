package com.asudak.pico.nbi.server.response;

public class MessageResponse extends RichResponse {

    private final String message;

    public MessageResponse(Status status, String message) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
