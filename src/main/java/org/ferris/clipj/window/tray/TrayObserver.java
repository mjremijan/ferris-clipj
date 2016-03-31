package org.ferris.clipj.window.tray;

import java.awt.TrayIcon;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.about.About;
import org.ferris.clipj.window.main.StartupEvent;
import static org.ferris.clipj.window.main.StartupEvent.TRAY;
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
    protected TrayIcon trayIcon;
    
    @Inject
    protected About about;

    public void observes(
        @Observes @Priority(TRAY) StartupEvent event
    ) {
        log.info("Show welcome popup");
        trayIcon.displayMessage(
              "Hotdog!"
            , String.format("ClipJ (%s)", about.getVersion())
            , TrayIcon.MessageType.NONE
        );
    }
}
