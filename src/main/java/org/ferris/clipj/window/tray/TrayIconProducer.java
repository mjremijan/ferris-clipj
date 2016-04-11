package org.ferris.clipj.window.tray;

import java.awt.Image;
import java.awt.TrayIcon;
import javax.enterprise.inject.Produces;
import org.ferris.clipj.window.image.qualifier.TrayImage;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayIconProducer {
    @Produces
    public TrayIcon produceTrayIcon(@TrayImage Image image) {
        return new TrayIcon(image);
    }
}
