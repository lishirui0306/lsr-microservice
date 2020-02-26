package cn.lsr.core.config.swagger;


/**
 * @Description: 丝袜哥配置类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class SwaggerConfig {
    private String controller;
    private String title;
    private String description;
    private String version;
    private String license;
    private String licenseUrl;

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }
}
