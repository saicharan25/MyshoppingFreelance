package info.devexchanges.navvp.adapter;


import info.devexchanges.navvp.model.Category;
import info.devexchanges.navvp.model.SubCategory;

public interface ItemClickListener {
    void itemClicked(Section section);

    void itemClicked(Category category);

    void itemClicked(SubCategory subCategory);
}
