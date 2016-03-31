package org.ferris.clipj.window.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import static java.lang.Thread.setDefaultUncaughtExceptionHandler;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.exit.ExitEvent;
import org.ferris.clipj.window.main.StartupEvent;
import static org.ferris.clipj.window.main.StartupEvent.EXCEPTION;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class UncaughtExceptionObserver implements UncaughtExceptionHandler {

    @Inject
    protected Logger log;

    @Inject
    protected UncaughtExceptionView uncaughtExceptionPage;

    @Inject
    protected Event<ExitEvent> exitEvent;

    public void observes(
        @Observes @Priority(EXCEPTION) StartupEvent event
    ) {
        log.info("Setting default uncaught exception handler");
        setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        uncaughtExceptionPage.view(e);
        exitEvent.fire(new ExitEvent());
    }
}
