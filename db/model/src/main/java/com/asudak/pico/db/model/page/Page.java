package com.asudak.pico.db.model.page;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Page<T> {

    private final List<T> content;
    private final boolean hasNext;
    private final int page;
    private final int size;
    private final int totalPages;

    private Page(List<T> content, int totalPages, boolean hasNext, int page, int size) {
        Objects.requireNonNull(content, "Page content can't be null.");

        this.content = content;
        this.hasNext = hasNext;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public List<T> getContent() {
        return content;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public <N> Page<N> map(Function<T, N> mapper) {
        return new Page(this.content.stream().map(mapper).collect(Collectors.toList()), totalPages, hasNext, page, size);
    }

    public static <T> Page of(List<T> content, int totalPages, boolean hasNext, PageRequest request) {
        return new Page(content, totalPages, hasNext, request.getPage(), request.getPageSize());
    }

    public static Page empty(PageRequest request) {
        return new Page(Collections.emptyList(), request.getPage(), false, request.getPage(), request.getPageSize());
    }
}