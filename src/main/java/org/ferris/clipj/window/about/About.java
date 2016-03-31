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

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    protected void setVersion(String version) {
        this.version = version;
    }

    public String getVendorId() {
        return vendorId;
    }

    protected void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getBuildJdk() {
        return buildJdk;
    }

    protected void setBuildJdk(String buildJdk) {
        this.buildJdk = buildJdk;
    }

    public String getVendor() {
        return vendor;
    }

    protected void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    protected void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    protected void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
