package info.devexchanges.navvp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import info.devexchanges.navvp.model.Item;
import info.devexchanges.navvp.model.Restaurant;

public class RestaurantSectionedExpandableLayoutHelper implements SectionStateChangeListener {
    public static LinkedHashMap<Section, ArrayList<Restaurant>> mSectionDataMap = new LinkedHashMap();
    List<Restaurant> categoriesList;
    private ArrayList<Object> mDataArrayList = new ArrayList();
    RecyclerView mRecyclerView;
    private HashMap<String, Restaurant> mSectionCategoryMap = new HashMap();
    private HashMap<String, Section> mSectionMap = new HashMap();
    private RestaurantSectionedExpandableGridAdapter mSectionedExpandableGridAdapter;

    public RestaurantSectionedExpandableLayoutHelper(Context context, RecyclerView recyclerView, ItemClickListener itemClickListener, int gridSpanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, gridSpanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        this.mSectionedExpandableGridAdapter = new RestaurantSectionedExpandableGridAdapter(context, this.mDataArrayList, gridLayoutManager, itemClickListener, this, this.categoriesList);
        recyclerView.setAdapter(this.mSectionedExpandableGridAdapter);
        this.mRecyclerView = recyclerView;
    }

    public void notifyDataSetChanged() {
        generateDataList();
        this.mSectionedExpandableGridAdapter.notifyDataSetChanged();
    }

    public void addSection(String section, Restaurant c, ArrayList<Restaurant> items, List<Restaurant> categories) {
        HashMap hashMap = this.mSectionMap;
        Section newSection = new Section(c, section);
        hashMap.put(section, newSection);
        mSectionDataMap.put(newSection, items);
        this.categoriesList = categories;
        this.mSectionCategoryMap.put(section, c);
    }

    public void addItem(String section, Restaurant item) {
        ((ArrayList) mSectionDataMap.get(this.mSectionMap.get(section))).add(item);
    }

    public void removeItem(String section, Item item) {
        ((ArrayList) mSectionDataMap.get(this.mSectionMap.get(section))).remove(item);
    }

    public void removeSection(String section) {
        mSectionDataMap.remove(this.mSectionMap.get(section));
        this.mSectionMap.remove(section);
    }

    private void generateDataList() {
        this.mDataArrayList.clear();
        for (Entry<Section, ArrayList<Restaurant>> entry : mSectionDataMap.entrySet()) {
            Section key = (Section) entry.getKey();
            this.mDataArrayList.add(key);
            if (key.isExpanded) {
                this.mDataArrayList.addAll((Collection) entry.getValue());
            }
        }
    }

    public void onSectionStateChanged(Section section, boolean isOpen) {
        if (!this.mRecyclerView.isComputingLayout()) {
            section.isExpanded = isOpen;
            notifyDataSetChanged();
        }
    }
}
