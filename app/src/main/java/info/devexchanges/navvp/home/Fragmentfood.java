package info.devexchanges.navvp.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.InternetDetector;
import info.devexchanges.navvp.Utils.ShowDialog;
import info.devexchanges.navvp.Utils.Urls;
import info.devexchanges.navvp.adapter.JSONfunctions;
import info.devexchanges.navvp.listview.API;
import info.devexchanges.navvp.listview.ActorAdapter;
import info.devexchanges.navvp.listview.frag;
import info.devexchanges.navvp.main.LoginActivity;
import info.devexchanges.navvp.main.MyApplication;
import info.devexchanges.navvp.model.Categories;
import info.devexchanges.navvp.model.Cities;
import info.devexchanges.navvp.model.Result;
import info.devexchanges.navvp.services.PostData;


/**
 * Created by acer on 9/15/2017.
 */

public class Fragmentfood extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    View view;
    ArrayList<HashMap<String, String>> arraylist;
    private ProgressDialog pdialog;
    private SliderLayout mDemoSlider;
    private ArrayList<String> arr_bannerlist;
    private ViewPager viewPager;
    private ArrayList<Result> arr_upcomingmovieslist;
    private ArrayList<Result> arr_bannermovieslist;
    private RecyclerView rv_items;
    JSONObject jsonobject;
    JSONArray jsonarray;
    String data;
    ProgressDialog mProgressDialog;
    ArrayList<String> worldlist = new ArrayList<String>();
    ArrayList<Cities> world = new ArrayList<Cities>();
    private TextView txtPostList;
    private ArrayList<Cities> beanPostArrayList;
    private StringBuffer postList;
    JSONObject jo;
    String id, city;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_food, container, false);
        Log.d("fraggg", "frag..........");
        pdialog = ShowDialog.showProgress(getActivity());
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider_maindashboard);
        getMainImage();
        new DownloadJSON().execute("http://honestshoppie.com/services/index.php/api/get_restaurants_by_city");
        // new DownloadJSON().execute();
        return view;
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

    private void getMainImage() {
        if (InternetDetector.isConnected(getActivity())) {

            pdialog.show();
            JSONObject sendData = new JSONObject();
            try {
                sendData.put(Urls.dashboard.status, "1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("dashboard_json", sendData.toString());
            Log.d("dashboard_json", Urls.dashboard.URL);
            JsonObjectRequest loginrequest = new JsonObjectRequest(Request.Method.POST, Urls.dashboard.URL, sendData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        pdialog.dismiss();
                        Log.d("dashboard_ressponse", response.toString());
                        //{"status":true,"token":"75^C#8cPZ$VODzLHA"}
                        setdashboardImages(response.toString());
                    } catch (Exception e) {
                        pdialog.dismiss();
                        Log.d("error", e.toString());
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    pdialog.dismiss();
                    Log.d("ERROR", "error => " + error.toString());
                }
            }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Urls.header, ShowDialog.getToken(getActivity()));


                    return params;
                }
            };

            loginrequest.setRetryPolicy(new DefaultRetryPolicy(Urls.DEFAULT_TIMEOUT_MS, Urls.DEFAULT_MAX_RETRIES, Urls.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().addRequestToQueue(loginrequest, "loginrequest");

        }
    }

    private void setdashboardImages(String s) {
        try {
            JSONObject data = new JSONObject(s);
            JSONArray arr_bannerimgs = data.getJSONArray("result");

            arr_bannerlist = new ArrayList<String>();
            arr_bannermovieslist = new ArrayList<Result>();

            for (int i = 0; i < arr_bannerimgs.length(); i++) {
                JSONObject dataimg = arr_bannerimgs.getJSONObject(i);
                if (dataimg.has("app_banner_image")) {
                    arr_bannerlist.add(dataimg.getString("app_banner_image"));
                }
                Result item = new Result();
                //  item.setImagename(dataimg.getString("image"));
                //   item.setApp_banner_image (dataimg.getString("app_banner_image"));
                arr_bannermovieslist.add(item);
            }

            for (int j = 0; j < arr_bannerlist.size(); j++) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
                // initialize a SliderLayout
                textSliderView
                        .image(arr_bannerlist.get(j))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putInt("extra", j);

                mDemoSlider.addSlider(textSliderView);
            }
            //.description("image" + j)
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(1000);
            mDemoSlider.addOnPageChangeListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        int pos = (int) slider.getBundle().get("extra");
        Result item = arr_bannermovieslist.get(pos);
        /* if (item.getImageurl().length() > 0) {
            Intent tt = new Intent(getActivity(), Activity_Moviedetails.class);
            tt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            tt.putExtra(getString(R.string.moviename), item.getImageurl());
            tt.putExtra(getString(R.string.moviestatus), "current");
            startActivity(tt);
        } else {
            ShowDialog.showToast("Url not found..!", getActivity());
        }*/

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // Download JSON file AsyncTask
    private class DownloadJSON extends AsyncTask<String, Void, Boolean> {
        String result;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* progressDialog=new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading spinner items...");
            progressDialog.show();*/
        }

        @Override
        protected Boolean doInBackground(String... urls) {

            List<Cities> ret = new ArrayList<Cities>();
            try {
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONArray ja = new JSONArray(data);
                    int n = ja.length();
                    for (int i = 0; i < n; i++) {
                        jsonobject = ja.getJSONObject(i);
                        Cities worldpop = new Cities();

                        worldpop.setCity(jsonobject.optString("city"));
                        worldpop.setId(jsonobject.optString("id"));
                        worldpop.setDelivery_amount(jsonobject.optString("delivery_amount"));
                        worldpop.setStatus(jsonobject.optString("status"));

                        world.add(worldpop);
                        // Populate spinner with country names
                        worldlist.add(jsonobject.optString("city"));
                        Log.d("city","city:"+jsonobject.optString("city"));
                    }



                }

                return true;

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

            return false;
        }


        protected void onPostExecute(Void args) {
            // Locate the spinner in activity_main.xml
            Spinner mySpinner1 = (Spinner) view.findViewById(R.id.city);
            Spinner mySpinner2 = (Spinner) view.findViewById(R.id.area);

            // Spinner adapter
            mySpinner1.setAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item,
                    worldlist));
            mySpinner2.setAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item,
                    worldlist));
            progressDialog.dismiss();

        }
    }

}

