package info.devexchanges.navvp.model;

import java.util.ArrayList;

public class Category {
    private String app_banner_image;
    private String banner_image;
    private String cat_id;
    public String category_name;
    private String description;
    public String icon_image;
    public boolean isExpanded = false;
    private ArrayList<SubCategory> subcategories;

    public Category(String name) {
        this.category_name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<SubCategory> getSubcategories() {
        return this.subcategories;
    }

    public void setSubcategories(ArrayList<SubCategory> subcategories) {
        this.subcategories = subcategories;
    }

    public String getCat_id() {
        return this.cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBanner_image() {
        return this.banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getIcon_image() {
        return this.icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getApp_banner_image() {
        return this.app_banner_image;
    }

    public void setApp_banner_image(String app_banner_image) {
        this.app_banner_image = app_banner_image;
    }
}
