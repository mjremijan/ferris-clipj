package org.ferris.clipj.window.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.enterprise.inject.Produces;
import org.ferris.clipj.window.image.qualifier.TrayImage;
import org.ferris.clipj.window.image.qualifier.AboutImage;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ImageProducer {

    private static final String trayImagePath = "/tray.png";

    private static final String aboutImagePath = "/about.png";

    private URL getResource(String path) {
        URL resource
            = getClass().getResource(path);

        if (resource == null) {
            throw new RuntimeException(String.format("The resource \"%s\" was not found on the class path", path));
        }

        return resource;
    }


    @Produces @TrayImage
    public Image produceTrayImage() {
        return
            Toolkit.getDefaultToolkit().createImage(
                getResource(trayImagePath)
            );
    }


    @Produces @AboutImage
    public Image produceAboutImage() {
        return
            Toolkit.getDefaultToolkit().createImage(
                getResource(aboutImagePath)
            );
    }
}
