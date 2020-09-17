package com.asudak.pico.nbi.security;

import com.asudak.pico.core.exception.ApplicationException;
import com.asudak.pico.core.util.Either;

import java.util.Optional;

/*
 TODO it's probably would be nice idea to seal this interface.
      Once this will be done orElseThrow block in toEither could be removed.
*/
public interface SecurityResponse {

    default boolean isSuccessful() {
        return this instanceof SuccessfulSecurityResponse;
    }

    default Optional<SuccessfulSecurityResponse> asSuccessfulResponse() {
        return Optional.of(this).filter(SecurityResponse::isSuccessful)
                .map(SuccessfulSecurityResponse.class::cast);
    }

    default boolean isFailed() {
        return this instanceof FailedSecurityResponse;
    }

    default Optional<FailedSecurityResponse> asFailedResponse() {
        return Optional.of(this).filter(SecurityResponse::isFailed)
                .map(FailedSecurityResponse.class::cast);
    }

    default Either<FailedSecurityResponse, SuccessfulSecurityResponse> toEither() {
        return asSuccessfulResponse().map(Either::right)
                .orElseGet(() -> asFailedResponse().map(Either::left)
                        .orElseThrow(() -> new ApplicationException("Security response should inherit one of the following interfaces: "
                                + SuccessfulSecurityResponse.class.getSimpleName() + ", " + FailedSecurityResponse.class.getSimpleName())));

    }
}
