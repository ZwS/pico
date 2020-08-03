package com.asudak.pico.db;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbConfiguration {

    @Inject
    @ConfigProperty(name = "pico.db.page.size")
    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }
}
