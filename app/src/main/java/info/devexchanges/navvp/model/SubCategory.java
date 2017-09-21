package info.devexchanges.navvp.model;

import java.util.Date;

public class SubCategory {
    private String cat_id;
    private Date date;
    private String icon_image;
    private String id;
    private String status;
    private String subcategory_name;

    public String getSubcategory_name() {
        return this.subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_id() {
        return this.cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getIcon_image() {
        return this.icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
