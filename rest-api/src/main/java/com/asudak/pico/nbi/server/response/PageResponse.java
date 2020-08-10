package com.asudak.pico.nbi.server.response;

import com.asudak.pico.db.model.page.Page;

import java.util.List;

public class PageResponse<T> extends RichResponse {

    private final List<T> content;
    private final boolean hasNext;
    private final int page;
    private final int totalPages;

    protected PageResponse(Page<T> page) {
        super(Status.SUCCESS);
        this.content = page.getContent();
        this.hasNext = page.hasNext();
        this.page = page.getPage();
        this.totalPages = page.getTotalPages();
    }

    public List<T> getContent() {
        return content;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page);
    }
}
