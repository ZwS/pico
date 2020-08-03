package com.asudak.pico.nbi.server.response;

public class ObjectResponse extends RichResponse {

    private final Object data;

    public ObjectResponse(Status status, Object data) {
        super(status);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
