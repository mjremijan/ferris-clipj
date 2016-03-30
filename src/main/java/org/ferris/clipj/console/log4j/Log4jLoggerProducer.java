package org.ferris.clipj.console.log4j;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.log4j.Logger;
/**
 * Produces {@link Logger} objects for injection using the CDI
 * {@link InjectionPoint} to get the name of the requesting class
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Log4jLoggerProducer {
    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getBean().getBeanClass());
    }
}
