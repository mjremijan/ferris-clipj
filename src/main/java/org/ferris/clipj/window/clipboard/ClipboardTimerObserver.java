package org.ferris.clipj.window.clipboard;

import java.util.Timer;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.clipj.window.main.StartupEvent;
import static org.ferris.clipj.window.main.StartupEvent.STARTUP_TIMER_TASK;
import org.jboss.weld.experimental.Priority;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ClipboardTimerObserver {

    @Inject
    protected Logger log;
    
    @Inject
    protected Timer timer;
    
    @Inject
    protected ClipboardTimerTask clipboardTimerTask;

    public void observes(@Observes @Priority(STARTUP_TIMER_TASK) StartupEvent event) {
        log.info("ENTER");
        timer.schedule(clipboardTimerTask, 0, 1000 * 2);
    }
}
