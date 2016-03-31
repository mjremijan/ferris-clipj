package org.ferris.clipj.window.tray;

import java.awt.TrayIcon;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayView {
    
    @Inject
    protected TrayIcon trayIcon;
    
    @PostConstruct
    public void putTheUiTogether() {
        trayIcon.setToolTip("ClipJ");
        trayIcon.setPopupMenu(new ClipboardHistoryMenu())));
        
        trayIcon.addActionListener(e -> System.out.println("clicked on clipj"));
        return myTrayIcon;
    }
}
