package org.ferris.clipj.window.logback;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Produces {@link Logger} objects for injection using the CDI
 * {@link InjectionPoint} to get the name of the requesting class
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class LogbackLoggerProducer {
    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getBean().getBeanClass());
    }
}
