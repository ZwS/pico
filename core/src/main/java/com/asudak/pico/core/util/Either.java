package com.asudak.pico.core.util;

import java.util.Optional;

public class Either<L, R> {

    private final Left<L> left;
    private final Right<R> right;

    private Either(L left, R right) {
        this.left = new Left<>(left);
        this.right = new Right<>(right);
    }

    public Optional<L> left() {
        return Optional.of(left).filter(Left.class::isInstance).flatMap(Side::value);
    }

    public Optional<R> right() {
        return Optional.of(right).filter(Right.class::isInstance).flatMap(Side::value);
    }

    public boolean isLeft() {
        return left().isPresent();
    }

    public boolean isRight() {
        return right().isPresent();
    }

    private static class Side<T> {

        private final Optional<T> value;

        public Side(T value) {
            this.value = Optional.ofNullable(value);
        }

        private Optional<T> value() {
            return value;
        }
    }

    private static class Left<T> extends Side<T> {
        public Left(T value) {
            super(value);
        }
    }

    private static class Right<T> extends Side<T> {
        public Right(T value) {
            super(value);
        }
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }
}
