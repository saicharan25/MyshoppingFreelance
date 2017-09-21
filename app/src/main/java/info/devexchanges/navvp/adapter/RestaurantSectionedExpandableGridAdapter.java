package info.devexchanges.navvp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.model.Restaurant;
import info.devexchanges.navvp.model.SubCategory;

public class RestaurantSectionedExpandableGridAdapter extends Adapter<RestaurantSectionedExpandableGridAdapter.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 2130968621;
    private static final int VIEW_TYPE_SECTION = 2130968670;
    private final List<Restaurant> categoriesList;
    private final Context mContext;
    private ArrayList<Object> mDataArrayList;
    private final ItemClickListener mItemClickListener;
    private final SectionStateChangeListener mSectionStateChangeListener;

    class C02683 implements OnClickListener {
        C02683() {
        }

        public void onClick(View v) {
        }
    }

    protected static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        RelativeLayout categoryRelativeLayout;
        TextView deliveyTextView;
        TextView descriptionTextView;
        ImageView iconImageView;
        ImageView iconRestImageView;
        ImageView iconSubImageView;
        ImageView indicatorImageView;
        TextView itemTextView;
        TextView sectionTextView;
        ToggleButton sectionToggleButton;
        TextView titleTextView;
        View view;
        int viewType;

        public ViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            this.view = view;
            if (viewType == R.layout.category_sub_layout_item) {
                this.itemTextView = (TextView) view.findViewById(R.id.text_item);
                this.iconSubImageView = (ImageView) view.findViewById(R.id.iv_sub_image);
                return;
            }
            this.sectionTextView = (TextView) view.findViewById(R.id.title);
            this.iconImageView = (ImageView) view.findViewById(R.id.iv_image);
           /* this.sectionToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button_section);
            this.descriptionTextView = (TextView) view.findViewById(R.id.details);
            this.indicatorImageView = (ImageView) view.findViewById(R.id.iv_indicator);
            this.categoryRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_category_item);*/
            this.titleTextView = (TextView) view.findViewById(R.id.movie_title);
          //  this.deliveyTextView = (TextView) view.findViewById(R.id.delivery);
            this.iconRestImageView = (ImageView) view.findViewById(R.id.iv_image);
        }
    }

    public RestaurantSectionedExpandableGridAdapter(Context context, ArrayList<Object> dataArrayList, final GridLayoutManager gridLayoutManager, ItemClickListener itemClickListener, SectionStateChangeListener sectionStateChangeListener, List<Restaurant> categories) {
        this.mContext = context;
        this.mItemClickListener = itemClickListener;
        this.mSectionStateChangeListener = sectionStateChangeListener;
        this.mDataArrayList = dataArrayList;
        this.categoriesList = categories;
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int position) {
                return RestaurantSectionedExpandableGridAdapter.this.isSection(position) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }

    private boolean isSection(int position) {
        return this.mDataArrayList.get(position) instanceof Section;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(viewType, parent, false), viewType);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            switch (holder.viewType) {
                case R.layout.category_sub_layout_item:
                    final SubCategory item = (SubCategory) this.mDataArrayList.get(position);
                    holder.itemTextView.setText(item.getSubcategory_name());
                    Picasso.with(this.mContext).load(item.getIcon_image()).into(holder.iconSubImageView);
                    holder.view.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                        //    RestaurantSectionedExpandableGridAdapter.this.mItemClickListener.itemClicked(item);
                        }
                    });
                    return;
                case R.layout.restaurant_list_item:
                    Section section = (Section) this.mDataArrayList.get(position);
                  //  Restaurant restaurant = section.getRestaurant();
                   // holder.sectionTextView.setText(section.getRestaurant().getRestaurant_name());
                    String desc = "";
                  /*  holder.deliveyTextView.setText("Delivery in " + restaurant.getDelivery_timings());
                    holder.descriptionTextView.setText(WordUtils.capitalizeFully(restaurant.getMenu_details()));
                    Picasso.with(this.mContext).load(((Section) this.mDataArrayList.get(position)).getRestaurant().getApp_banner()).error((int) R.drawable.bag_512x512).into(holder.iconImageView);
                   */ holder.categoryRelativeLayout.setOnClickListener(new C02683());
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.mDataArrayList.size();
    }

    public int getItemViewType(int position) {
        if (isSection(position)) {
            return R.layout.restaurant_list_item;
        }
        return R.layout.category_sub_layout_item;
    }
}
