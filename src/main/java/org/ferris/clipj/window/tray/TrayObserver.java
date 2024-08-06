package org.ferris.clipj.window.tray;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.clipj.window.history.HistoryEvent;
import org.ferris.clipj.window.main.StartupEvent;
import static org.ferris.clipj.window.main.StartupEvent.STARTUP_GUI;
import org.jboss.weld.experimental.Priority;
import org.slf4j.Logger;


/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayObserver {

    @Inject
    protected Logger log;

    @Inject
    protected TrayView view;

    public void startupGui(@Observes @Priority(STARTUP_GUI) StartupEvent event) {
        log.info("ENTER");
        view.startup();
    }
    
    public void historyHasNewItem(@Observes HistoryEvent event) {
        log.info("ENTER");
        view.viewNewHistoryItem(event.getNewHistoryItem());
    }
}
