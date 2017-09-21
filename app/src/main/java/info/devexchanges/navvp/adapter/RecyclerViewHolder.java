package info.devexchanges.navvp.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.devexchanges.navvp.R;


/* compiled from: RecyclerView_Adapter_Horizontal */
class RecyclerViewHolder extends ViewHolder {
    public ImageView imageview;
    public TextView redtextview;
    public TextView title;

    public RecyclerViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.redtextview = (TextView) view.findViewById(R.id.redtextview);
        this.imageview = (ImageView) view.findViewById(R.id.image);
    }
}
