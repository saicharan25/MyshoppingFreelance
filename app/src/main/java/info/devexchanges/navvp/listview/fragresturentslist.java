package info.devexchanges.navvp.listview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.InternetDetector;
import info.devexchanges.navvp.Utils.ShowDialog;
import info.devexchanges.navvp.Utils.Urls;
import info.devexchanges.navvp.home.Fragmentfood;
import info.devexchanges.navvp.main.MyApplication;
import info.devexchanges.navvp.model.Result;

/**
 * Created by acer on 9/17/2017.
 */

public class fragresturentslist extends Fragment {
    View view;
    ArrayList<HashMap<String, String>> arraylist;
    JSONObject jsonobject;
    JSONArray jsonarray;
    private ProgressDialog pdialog;
    private SliderLayout mDemoSlider;
    private ArrayList<String> arr_bannerlist;
    private ViewPager viewPager;
    ProgressDialog mProgressDialog;
    private ArrayList<Result> arr_upcomingmovieslist;
    private ArrayList<Result> arr_bannermovieslist;
    private RecyclerView rv_items;
    ArrayList<String> worldlist;
    String OutputData;
    Button bparsejson;
    ListView lv;
    ArrayList<Result> actorsList;
    ActorAdapter adapter;
    ArrayList<String> outputDataList = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View vw=inflater.inflate(R.layout.listview_main, container, false);
        lv=(ListView) vw.findViewById(R.id.listview);

        actorsList = new ArrayList<Result>();
        new JSONAsynTask().execute("http://honestshoppie.com/services/index.php/api/get_restaurants_by_city");
        adapter = new ActorAdapter(getActivity(), R.layout.list_rest, actorsList);

        lv.setAdapter(adapter);
        return  vw;
    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("fraggg", "frag3");
    }


    class JSONAsynTask extends AsyncTask<String, Void, Boolean> {
        String result;
        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("please wait....");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("result");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Result actor = new Result();

                        actor.setRestaurant_name(object.getString("restaurant_name"));
                        actor.setAddress(object.getString("address"));
                        actor.setApp_banner(object.getString("app_banner"));

                        actorsList.add(actor);
                    }
                    return true;
                }

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;

        }

        protected void onPostExecute(Boolean result) {

            dialog.dismiss();
            adapter.notifyDataSetChanged();
            if (result == false)
                Toast.makeText(getActivity(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }

}