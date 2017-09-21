package info.devexchanges.navvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import info.devexchanges.navvp.R;
import info.devexchanges.navvp.model.Result;
import info.devexchanges.navvp.model.Result;

import static info.devexchanges.navvp.home.MainActivity.mContext;

public class CouponsAdapater extends RecyclerView.Adapter<CouponsAdapater.MyViewHolder> {

    private List<Result> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category_name;
        public ImageView thumbnail;


        public MyViewHolder(View view) {
            super(view);
            category_name = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.image);
        }
    }

    public CouponsAdapater(List<Result> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_home, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result movie = moviesList.get(position);
        holder.category_name.setText(movie.getCategory_name());
        // loading album cover using Glide library
       // Glide.with(mContext).load(movie.getIcon_image()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

