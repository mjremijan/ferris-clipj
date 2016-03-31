package org.ferris.clipj.window.about;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class AboutProducer {

    @Produces
    public About produceAbout() {
        // This is an example of a JarUrlConnection URL
        //    URL: jar:file:/C:/Users/Michael/..../jboss-annotations-api_1.2_spec-1.0.0.Final.jar!/META-INF/MANIFEST.MF
        //
        // This is what the jarURL returns above
        // jarURL:     file:/C:/Users/Michael/..../ferris-riviera-2.0.0.0-SNAPSHOT-windows.jar    
        Attributes attributes
            = new Attributes();

        try {
            URL jarURL
                = this.getClass().getProtectionDomain().getCodeSource().getLocation();

            URI manifestUri
                = new URI(String.format("jar:%s!/%s", jarURL, JarFile.MANIFEST_NAME));

            InputStream is = manifestUri.toURL().openStream();
            Manifest manifest = new Manifest(is);
            attributes = manifest.getMainAttributes();
            is.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        About about = new About();
        {
            about.setTitle(attributes.getValue("Implementation-Title"));
            about.setVersion(attributes.getValue("Implementation-Version"));
            about.setUrl(attributes.getValue("Implementation-URL"));
            about.setBuildJdk(attributes.getValue("Build-Jdk"));
            about.setCreatedBy(attributes.getValue("Created-By"));
            about.setCreatedOn(attributes.getValue("Build-Time"));
            about.setVendorId(attributes.getValue("Implementation-Vendor-Id"));
            about.setVendor(attributes.getValue("Implementation-Vendor"));
        }
        return about;
    }
}
