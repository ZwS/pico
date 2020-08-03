package com.asudak.pico.db.model.page;

public class PageRequest {

    private final int page;
    private final int pageSize;

    private PageRequest(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return page * pageSize;
    }

    public static PageRequest of(int page, int pageSize) {
        return new PageRequest(page, pageSize);
    }
}
