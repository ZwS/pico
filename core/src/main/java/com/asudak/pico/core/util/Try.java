package com.asudak.pico.core.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Try<T> {

    private final Either<Exception, T> result;

    private Try(Either<Exception, T> result) {
        this.result = result;
    }

    public Either<Exception, T> asEither() {
        return result;
    }

    public Optional<T> asOptional() {
        return result.right();
    }

    public Try<T> handle(Function<Exception, T> handler) {
        return handle(Exception.class, handler);
    }

    public <E extends Exception> Try<T> handle(Class<E> exception, Function<E, T> handler) {
        return result.left()
                .filter(exception::isInstance)
                .map(exception::cast)
                .map(handler::apply)
                .map(Either::right)
                .map(Try<T>::new)
                .orElse(this);
    }

    public static <T> Try<T> of(Supplier<T> supplier) {
        try {
            return new Try<>(Either.right(supplier.get()));
        } catch (Exception e) {
            return new Try<>(Either.left(e));
        }
    }
}
