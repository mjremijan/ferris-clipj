package org.ferris.clipj.window.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.about.About;
import org.ferris.clipj.window.image.qualifier.TrayImage;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayView {

    @Inject
    protected Logger log;

    @Inject
    protected About about;

    @Inject
    @TrayImage
    protected Image image;

    @Inject
    protected TrayPopupMenu popupMenu;

    protected TrayIcon trayIcon;

    @PostConstruct
    public void putTheUiTogether() {
        trayIcon = new TrayIcon(image);
        trayIcon.setToolTip(String.format("Ferris ClipJ (%s)", about.getVersion()));
        trayIcon.setPopupMenu(popupMenu);
        //trayIcon.addActionListener(e -> System.out.println("clicked on clipj"));
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

}
