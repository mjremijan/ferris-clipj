package org.ferris.clipj.window.tray;

import java.awt.TrayIcon;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayIconProducer {

    private TrayIcon trayIcon;
    
    @Produces
    public TrayIcon produceTrayIcon() {
        if (trayIcon == null) {
            trayIcon = new TrayIcon(getImage(), "ClipJ", new ClipboardHistoryMenu());
        }
        trayIcon.addActionListener(e -> System.out.println("clicked on clipj"));
        return myTrayIcon;
    }
}
