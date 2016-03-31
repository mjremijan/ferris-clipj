package org.ferris.clipj.window.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.ferris.clipj.window.image.qualifier.About;
import org.ferris.clipj.window.image.qualifier.Tray;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
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
    
    
    @Produces @Tray
    public Image produceTrayImage() {
        return 
            Toolkit.getDefaultToolkit().createImage(
                getResource(trayImagePath)
            );
    }
    
    
    @Produces @About
    public Image produceAboutImage() {
        return 
            Toolkit.getDefaultToolkit().createImage(
                getResource(aboutImagePath)
            );
    }
}
