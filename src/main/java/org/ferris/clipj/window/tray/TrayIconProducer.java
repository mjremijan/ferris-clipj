package org.ferris.clipj.window.tray;

import java.awt.Image;
import java.awt.TrayIcon;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.ferris.clipj.window.image.qualifier.Tray;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayIconProducer {

    @Produces
    public TrayIcon produceTrayIcon(@Tray Image image) {
        return new TrayIcon(image);
    }
}
