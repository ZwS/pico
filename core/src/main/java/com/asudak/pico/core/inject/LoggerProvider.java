package com.asudak.pico.core.inject;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import java.util.logging.Logger;

@Singleton
public class LoggerProvider {

    @Produces
    public Logger provideLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getBean().getBeanClass().getSimpleName());
    }
}
