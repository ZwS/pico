package com.asudak.pico.nbi.server.response;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class ObjectResponse<T> extends RichResponse {

    private final T data;

    @JsonbCreator
    public ObjectResponse(
            @JsonbProperty("status") Status status,
            @JsonbProperty("data") T data
    ) {
        super(status);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
