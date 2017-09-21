package info.devexchanges.navvp.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.ShowDialog;
import info.devexchanges.navvp.model.Result;

/**
 * Created by sandeep on 25/5/17.
 */

public class GridAdapter extends BaseAdapter {
    List<Result> list = new ArrayList<Result>();

    Context contxt;
    String homestatus;
    private String str_token;
    private ProgressDialog PDialog;

    View view;
    public GridAdapter(List<Result> lists, Context con, String status) {
        this.list = lists;
        this.contxt = con;
        this.homestatus = status;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            // view = new View(contxt);
            view = inflater.inflate(R.layout.listview_item, null);
            ImageView img = (ImageView) view.findViewById(R.id.flag);
            TextView rank = (TextView) view.findViewById(R.id.rank);

            final Result item = list.get(i);

            PDialog = ShowDialog.showProgress(contxt);

            rank.setText(item.getCategory_name());
           /* Uri uri = Uri.parse(item.getImagesrc());
            holder.img.setImageURI(uri);*/
            ShowDialog.setImagePicasso(item.getIcon_image(), img, contxt);
            //Log.d("wishlisys_item",""+item.getImagename());


        }
        return view;

    }

}
