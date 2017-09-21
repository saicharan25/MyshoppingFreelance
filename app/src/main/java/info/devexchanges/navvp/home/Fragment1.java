package info.devexchanges.navvp.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.InternetDetector;
import info.devexchanges.navvp.Utils.RoundedImageView;
import info.devexchanges.navvp.Utils.ShowDialog;
import info.devexchanges.navvp.Utils.Urls;
import info.devexchanges.navvp.adapter.TabAdapter;
import info.devexchanges.navvp.customviews.CustomFonts;
import info.devexchanges.navvp.listview.frag;
import info.devexchanges.navvp.main.MyApplication;
import info.devexchanges.navvp.model.Result;
import info.devexchanges.navvp.model.UpcomingMovies;


public class Fragment1 extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    View view;
    private ProgressDialog pdialog;
    private SliderLayout mDemoSlider;
    private ArrayList<String> arr_bannerlist;
    private RoundedImageView img_header;
    private GridView gridview;
    private ViewPager viewPager;
    private ArrayList<Result> arr_upcomingmovieslist;
    private ArrayList<Result> arr_bannermovieslist;
    ResideLayout resideLayout;
    private CustomFonts tv_credits;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRestoredFromBackstack;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home, container, false);
        Log.d("fraggg", "frag..........");
        initView();
        // Inflate the layout for this fragment
        // getProfileDetails();
        getMainImage();
        return view;
    }

    private void initView() {
        pdialog = ShowDialog.showProgress(getActivity());
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider_maindashboard);
        viewPager = (ViewPager) view.findViewById(R.id.pager_home_dashboard);
        //   mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

    private void setupViewPager(ViewPager viewPager) {

        TabAdapter adapter = new TabAdapter(getFragmentManager());
        adapter.addFragment(new frag(), "SHOPPING");
        //    adapter.addFragment(new WatchTrailer(dtat_movie.toString()), "Trailer");
        viewPager.setAdapter(adapter);


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

           /* Log.d("banner list", "" + arr_bannerlist);
            SlidingImageAdapter adapterView = new SlidingImageAdapter(arr_bannerlist, getApplicationContext());
            main_viewpager.setAdapter(adapterView);*/

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
            mDemoSlider.setDuration(4000);
            mDemoSlider.addOnPageChangeListener(this);

            //   main_viewpager.setCurrentItem(getArguments().getInt(Constants.Extra.IMAGE_POSITION, 0));

           /* RecyclarAdapter addapter = new RecyclarAdapter(arr_upcomingmovieslist, "home", this);
            recyclar.setAdapter(addapter);*/
            if (viewPager != null) {
                Log.d("frag", "view");
                setupViewPager(viewPager);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        int pos = (int) slider.getBundle().get("extra");
        Result item = arr_bannermovieslist.get(pos);
       // if (item.getImageurl().length() > 0) {
           /* Intent tt = new Intent(getActivity(), Activity_Moviedetails.class);
            tt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            tt.putExtra(getString(R.string.moviename), item.getImageurl());
            tt.putExtra(getString(R.string.moviestatus), "current");
            startActivity(tt);*/
      /*  } else {
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
}

