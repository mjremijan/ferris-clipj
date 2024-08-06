package org.ferris.clipj.window.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import static java.lang.Thread.setDefaultUncaughtExceptionHandler;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.clipj.window.exit.ExitEvent;
import org.ferris.clipj.window.main.StartupEvent;
import static org.ferris.clipj.window.main.StartupEvent.STARTUP_EXCEPTION_HANDLING;
import org.jboss.weld.experimental.Priority;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionObserver implements UncaughtExceptionHandler {

    @Inject
    protected Logger log;

    @Inject
    protected UncaughtExceptionView uncaughtExceptionView;

    @Inject
    protected Event<ExitEvent> exitEvent;

    public void setTheDefaultUncaughtExceptionHandler(
        @Observes @Priority(STARTUP_EXCEPTION_HANDLING) StartupEvent event
    ) {
        log.info("ENTER");
        setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        uncaughtExceptionView.view(e);
        exitEvent.fire(new ExitEvent());
    }
}
