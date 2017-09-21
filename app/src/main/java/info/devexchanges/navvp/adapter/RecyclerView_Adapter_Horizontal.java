package info.devexchanges.navvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import java.util.ArrayList;
import info.devexchanges.navvp.R;
import info.devexchanges.navvp.datasource.Data_Model;
import info.devexchanges.navvp.main.FullDetails;
import info.devexchanges.navvp.model.Result;

public class RecyclerView_Adapter_Horizontal extends Adapter<RecyclerViewHolder> {
    private ArrayList<Result> arrayList;
    private Context context;

    class C02661 implements OnClickListener {
        C02661() {
        }

        public void onClick(View v) {
            RecyclerView_Adapter_Horizontal.this.context.startActivity(new Intent(RecyclerView_Adapter_Horizontal.this.context,
                    FullDetails.class));
        }
    }

    public RecyclerView_Adapter_Horizontal(Context context, ArrayList<Result> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public int getItemCount() {
        return this.arrayList != null ? this.arrayList.size() : 0;
    }

    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Result model = (Result) this.arrayList.get(position);
        RecyclerViewHolder mainHolder = holder;
      //  Bitmap image = BitmapFactory.decodeResource(this.context.getResources(), model.getIcon_image());
        mainHolder.redtextview.setPaintFlags(mainHolder.redtextview.getPaintFlags() | 16);
        mainHolder.title.setText(model.getCategory_name());
       // mainHolder.imageview.setImageBitmap(image);
       // mainHolder.imageview.setOnClickListener(new C02661());
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new RecyclerViewHolder((ViewGroup) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.horizontal_home, viewGroup, false));
    }
}
