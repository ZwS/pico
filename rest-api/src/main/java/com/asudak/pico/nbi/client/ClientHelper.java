package com.asudak.pico.nbi.client;

import com.asudak.pico.core.util.Try;
import com.asudak.pico.nbi.client.exception.NotFoundClientException;

import java.util.Optional;
import java.util.function.Supplier;

public class ClientHelper {

    private ClientHelper() {
    }

    public static <T> Optional<T> handlingNotFound(Supplier<T> apiCall) {
        return Try.of(apiCall)
                .handle(NotFoundClientException.class, e -> null)
                .asOptional();
    }
}
