package com.asudak.pico.nbi.server.response;

import java.util.List;

public class PageResponse<T> extends RichResponse {

    private final List<T> content;
    private final boolean hasNext;
    private final int page;
    private final int totalPages;

    protected PageResponse() {
        super(Status.SUCCESS);
    }
}
