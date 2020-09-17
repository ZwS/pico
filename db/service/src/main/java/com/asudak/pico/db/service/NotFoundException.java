package com.asudak.pico.db.service;

import java.util.Objects;

public class NotFoundException extends RuntimeException {

    private NotFoundException(Class<?> clazz, String field, String value) {
        super(String.format("%s with %s %s not found.", clazz, field, value));
    }

    public static Builder builder(Class<?> type) {
        return new Builder(type);
    }

    public static class Builder {
        private Class<?> clazz;
        private String field = "id";
        private String value;

        public Builder(Class<?> type) {
            this.clazz = type;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public NotFoundException build() {
            Objects.requireNonNull(clazz, "Class should be not null.");
            Objects.requireNonNull(field, "Field should be not null.");
            Objects.requireNonNull(value, "Value should be not null.");

            return new NotFoundException(clazz, field, value);
        }
    }
}
