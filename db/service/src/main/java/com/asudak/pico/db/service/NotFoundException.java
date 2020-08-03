package com.asudak.pico.db.service;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz, String id) {
        super(String.format("%s with id %s not found.", clazz, id));
    }
}
