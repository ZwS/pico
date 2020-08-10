package com.asudak.pico.nbi.server.response;

import java.util.List;

public class ValidationResponse extends RichResponse {

    private final List<Violation> violations;

    protected ValidationResponse(List<Violation> violations) {
        super(Status.WARNING);
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public static class Violation {

        private final String field;
        private final String message;

        private Violation(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

        public static Violation of(String field, String message) {
            return new Violation(field, message);
        }
    }
}
