package org.ferris.clipj.window.about;

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
    private String url;
    private String createdBy;
    private String createdOn;

    public About(String title, String version, String vendorId, String buildJdk, String vendor, String url, String createdBy, String createdOn) {
        this.title = title;
        this.version = version;
        this.vendorId = vendorId;
        this.buildJdk = buildJdk;
        this.vendor = vendor;
        this.url = url;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getBuildJdk() {
        return buildJdk;
    }

    public String getVendor() {
        return vendor;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }
}
