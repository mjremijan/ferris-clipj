package org.ferris.clipj.window.tray;

import java.awt.Image;
import java.awt.TrayIcon;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.ferris.clipj.window.about.About;
import org.ferris.clipj.window.image.qualifier.TrayImage;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayIconProducer {
    @Produces
    public TrayIcon produceTrayIcon(@TrayImage Image image, About about, TrayPopupMenu popupMenu) {
        TrayIcon ti = new TrayIcon(image);
        ti.setToolTip(String.format("Ferris ClipJ (%s)", about.getVersion()));
        ti.setPopupMenu(popupMenu);
        return ti;
    }
}
