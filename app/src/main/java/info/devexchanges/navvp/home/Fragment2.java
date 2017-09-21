package info.devexchanges.navvp.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;
import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.MyFriendsM;
import info.devexchanges.navvp.Utils.Urls;
import info.devexchanges.navvp.adapter.CouponsAdapater;
import info.devexchanges.navvp.adapter.JSONfunctions;
import info.devexchanges.navvp.adapter.ListViewAdapter;
import info.devexchanges.navvp.model.Result;
import info.devexchanges.navvp.services.jsonContent;

public class Fragment2 extends Fragment implements ViewPager.OnPageChangeListener, OnPageClickListener {

    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView listViewMyFriend;
    private int limit = 5;
    private int startFrom = 0;
    private Gson gson;
    private Urls.Login login;
    private ProgressDialog progressDialog;
    private TextView Counter;
    private MyFriendsM myFriendsModel;
    public CouponsAdapater mAdapter;
    private List<Result> movieList;
    public static final String[] TITLES = {"Hood", "Full Sleeve Shirt", "Shirt", "Jean Jacket", "Jacket"};
    public static final Integer[] IMAGES = {R.drawable.icon_film, R.drawable.movie_icon,
            R.drawable.icon_film, R.drawable.movie_icon, R.drawable.icon_film,};

    private ListViewAdapter adapter;
    private View myFragmentView;
    ArrayList<HashMap<String, String>> arraylist;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    // URL to get contacts JSON
    private static String url = "http://honestshoppie.com/services/index.php//api/allcategories";
    List<jsonContent> listcontent = new ArrayList<>();
    public jsonContent content;
    JSONObject jsonobject;
    JSONArray jsonarray;
    View view;
    Context context;
    ListView listview;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(String movieTitle) {
        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_listview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new GetContacts().execute();


        try {
//            Glide.with(this).load(R.drawable.ic_cart_black_24dp).into((ImageView)view. findViewById(R.id.image));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPageSelected(int position) {
        //do something
    }

    @Override
    public void onPageClick(int position, Page page) {
        //do something
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
          /*  HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        jsonContent content = new jsonContent();

                        content.name = c.getString("category_name");
                        content.email = c.getString("icon_image");

                        // JSONObject phone = c.getJSONObject("phone");
                        // content.mobile=phone.getString("mobile");

                        listcontent.add(content);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;*/
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            jsonobject = JSONfunctions.getJSONfromURL("http://honestshoppie.com/services/index.php/api/allcategories");
            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("result");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    Log.d("map1", "map1:" + map);
                    Log.d("jsonobject", "jsonobject:" + jsonobject);
                    // Retrive JSON Objects
                    if (jsonobject.has("category_name")) {
                        map.put("category_name", jsonobject.getString("category_name"));
                    }
                    if (jsonobject.has("icon_image")) {
                        map.put("icon_image", jsonobject.getString("icon_image"));
                    }
                    // Set the JSON Objects into the array
                    Log.d("arraylist", "arraylist:" + arraylist);
                    arraylist.add(map);
                    Log.d("map", "map:" + map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            listview = (ListView) view.findViewById(R.id.list);
            adapter = new ListViewAdapter(getActivity(), arraylist);
            try{
                listview.setAdapter(adapter);
            }catch(Exception e){
               Toast.makeText(getActivity(),"error: "+e.toString(),Toast.LENGTH_LONG).show();
            }

            pDialog.dismiss();
        }

    }

}
