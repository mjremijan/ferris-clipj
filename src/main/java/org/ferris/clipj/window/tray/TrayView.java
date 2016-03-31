package org.ferris.clipj.window.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.about.About;
import org.ferris.clipj.window.image.qualifier.TrayImage;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayView {

    @Inject
    protected Logger log;
    
    @Inject
    protected TrayIcon trayIcon;
    
    @Inject
    protected About about;
    
    @Produces
    public TrayIcon produceTrayIcon(@TrayImage Image image) {
        TrayIcon ti = new TrayIcon(image);
        ti.setToolTip(String.format("ClipJ (%s)", about.getVersion()));
        return ti;
    }

    public void startup() {
        log.info("ENTER");
        
        SystemTray tray 
            = SystemTray.getSystemTray();
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

//    @PostConstruct
//    public void putTheUiTogether() {
//        trayIcon.setToolTip("ClipJ");
//        trayIcon.setPopupMenu(new ClipboardHistoryMenu())));
//        
//        trayIcon.addActionListener(e -> System.out.println("clicked on clipj"));
//        return myTrayIcon;
}
