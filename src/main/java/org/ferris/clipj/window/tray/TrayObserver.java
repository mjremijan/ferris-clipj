package org.ferris.clipj.window.tray;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.main.StartupEvent;
import static org.ferris.clipj.window.main.StartupEvent.STARTUP_TRAY;
import org.jboss.weld.experimental.Priority;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayObserver {

    @Inject
    protected Logger log;
    
    @Inject
    protected TrayView view;
    
    public void observes(@Observes @Priority(STARTUP_TRAY) StartupEvent event) {
        log.info("ENTER");
        view.startup();
    }
}
