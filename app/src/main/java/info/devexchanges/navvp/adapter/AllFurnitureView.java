package info.devexchanges.navvp.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


import java.util.ArrayList;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.datasource.Data_Model;

public class AllFurnitureView extends AppCompatActivity {
    public static final Integer[] IMAGES = new Integer[]{Integer.valueOf(R.drawable.icon_film), Integer.valueOf(R.drawable.movie_icon), Integer.valueOf(R.drawable.icon_film), Integer.valueOf(R.drawable.movie_icon), Integer.valueOf(R.drawable.icon_film)};
    public static final String[] TITLES = new String[]{"Hood", "Full Sleeve Shirt", "Shirt", "Jean Jacket", "Jacket"};
    private static RecyclerView recyclerView;
    private static RecyclerView recyclerView1;

    class C02621 implements OnClickListener {
        C02621() {
        }

        public void onClick(View v) {
            AllFurnitureView.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_all_furniture_view);
        ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new C02621());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        populatRecyclerView();
    }

    private void populatRecyclerView() {
        ArrayList<Data_Model> arrayList = new ArrayList();
        for (int i = 0; i < TITLES.length; i++) {
            arrayList.add(new Data_Model(TITLES[i], IMAGES[i].intValue()));
        }
        /*RecyclerView_Adapter_Horizontal adapter = new RecyclerView_Adapter_Horizontal(this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/
    }
}
