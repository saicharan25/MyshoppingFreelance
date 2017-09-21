package info.devexchanges.navvp.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.home.MainActivity;
import info.devexchanges.navvp.model.Category;
import info.devexchanges.navvp.model.SubCategory;

public class CategoriesAdapter extends Adapter<CategoriesAdapter.MyViewHolder> {
    private List<Category> categoryList;

    class C02631 implements OnClickListener {
        C02631() {
        }

        public void onClick(View v) {
            Log.i("Hi", "jii");
            MainActivity.mContext.startActivity(new Intent(MainActivity.mContext, AllFurnitureView.class));
        }
    }

    class C02642 implements OnClickListener {
        C02642() {
        }

        public void onClick(View v) {
            Log.i("Hi", "jii");
            MainActivity.mContext.startActivity(new Intent(MainActivity.mContext, AllFurnitureView.class));
        }
    }

    public class MyViewHolder extends ViewHolder {
        public TextView genre;
        ImageView ivImage;
        public RelativeLayout rlCategoryItem;
        public TextView title;
        public TextView year;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.genre = (TextView) view.findViewById(R.id.genre);
            this.year = (TextView) view.findViewById(R.id.year);
            this.ivImage = (ImageView) view.findViewById(R.id.iv_image);
            this.rlCategoryItem = (RelativeLayout) view.findViewById(R.id.rl_category_item);
        }
    }

    public CategoriesAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        itemView.setOnClickListener(new C02631());
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category movie = (Category) this.categoryList.get(position);
        String desc = "";
        Iterator it = movie.getSubcategories().iterator();
        while (it.hasNext()) {
            desc = desc + ((SubCategory) it.next()).getSubcategory_name() + ", ";
        }
        movie.setDescription(desc);
        holder.title.setText(movie.getCategory_name());
        holder.genre.setText(movie.getDescription());
        holder.year.setText("");
        Picasso.with(MainActivity.mContext).load("http://honestshoppie.com/admin/cat_images/" + movie.getIcon_image()).into(holder.ivImage);
        holder.rlCategoryItem.setOnClickListener(new C02642());
    }

    public int getItemCount() {
        if (this.categoryList == null) {
            return 0;
        }
        return this.categoryList.size();
    }
}
