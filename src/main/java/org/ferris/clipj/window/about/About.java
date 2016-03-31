package org.ferris.clipj.window.about;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class About {

    private String title;
    private String version;
    private String vendorId;
    private String buildJdk;
    private String vendor;
    public String url;
    public String createdBy;
    public String createdOn;

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    private void setVersion(String version) {
        this.version = version;
    }

    public String getVendorId() {
        return vendorId;
    }

    private void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getBuildJdk() {
        return buildJdk;
    }

    private void setBuildJdk(String buildJdk) {
        this.buildJdk = buildJdk;
    }

    public String getVendor() {
        return vendor;
    }

    private void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    private void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    private void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public About() {
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
        }

        this.setTitle(attributes.getValue("Implementation-Title"));
        this.setVersion(attributes.getValue("Implementation-Version"));
        this.setUrl(attributes.getValue("Implementation-URL"));
        this.setBuildJdk(attributes.getValue("Build-Jdk"));
        this.setCreatedBy(attributes.getValue("Created-By"));
        this.setCreatedOn(attributes.getValue("Build-Time"));
        this.setVendorId(attributes.getValue("Implementation-Vendor-Id"));
        this.setVendor(attributes.getValue("Implementation-Vendor"));
    }
}
