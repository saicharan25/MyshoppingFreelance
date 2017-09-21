package info.devexchanges.navvp.adapter;


import info.devexchanges.navvp.model.Category;
import info.devexchanges.navvp.model.Restaurant;

public class Section {
    private Category category;
    public boolean isExpanded;
    private String name;
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Category getCategory() {
        return this.category;
    }

    public Section(String name) {
        this.name = name;
        this.isExpanded = false;
    }

    public Section(Category c) {
        this.category = c;
        this.isExpanded = false;
    }

    public Section(Category c, String section) {
        this.name = this.name;
        this.category = c;
        this.isExpanded = false;
    }

    public Section(Restaurant c, String section) {
        this.name = this.name;
        this.restaurant = c;
        this.isExpanded = false;
    }

    public void setExpanded(boolean expanded) {
        this.isExpanded = expanded;
    }

    public String getName() {
        return this.name;
    }
}
