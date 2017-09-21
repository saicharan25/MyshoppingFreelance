package info.devexchanges.navvp.model;

public class Restaurant {
    private String address;
    private String app_banner;
    private String city;
    private String courirer_id;
    private String delivery_timings;
    private String discount;
    private String id;
    private String menu_details;
    private String restaurant_name;
    private String restaurant_timings;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourirer_id() {
        return this.courirer_id;
    }

    public void setCourirer_id(String courirer_id) {
        this.courirer_id = courirer_id;
    }

    public String getRestaurant_name() {
        return this.restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getApp_banner() {
        return this.app_banner;
    }

    public void setApp_banner(String app_banner) {
        this.app_banner = app_banner;
    }

    public String getRestaurant_timings() {
        return this.restaurant_timings;
    }

    public void setRestaurant_timings(String restaurant_timings) {
        this.restaurant_timings = restaurant_timings;
    }

    public String getDelivery_timings() {
        return this.delivery_timings;
    }

    public void setDelivery_timings(String delivery_timings) {
        this.delivery_timings = delivery_timings;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMenu_details() {
        return this.menu_details;
    }

    public void setMenu_details(String menu_details) {
        this.menu_details = menu_details;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
