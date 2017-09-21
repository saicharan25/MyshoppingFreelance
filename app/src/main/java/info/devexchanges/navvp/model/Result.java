package info.devexchanges.navvp.model;

/**
 * Created by acer on 9/12/2017.
 */

public class Result {

    private String cat_id;

    private String category_name;

    private String icon_image;

    private String banner_image;

    private String[] subcategories;

    private String app_banner_image;

    private String address;

    private String restaurant_name;

    private String app_banner;

    public String getApp_banner ()
    {
        return app_banner;
    }

    public void setApp_banner (String app_banner)
    {
        this.app_banner = app_banner;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(String[] subcategories) {
        this.subcategories = subcategories;
    }

    public String getApp_banner_image() {
        return app_banner_image;
    }

    public void setApp_banner_image(String app_banner_image) {
        this.app_banner_image = app_banner_image;
    }

    @Override
    public String toString() {
        return "ClassPojo [cat_id = " + cat_id + ", category_name = " + category_name + ", icon_image = " + icon_image + ", banner_image = " + banner_image + ", subcategories = " + subcategories + ", app_banner_image = " + app_banner_image + "]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
