package info.devexchanges.navvp.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.adapter.JSONfunctions;
import info.devexchanges.navvp.adapter.ListViewAdapter;
import info.devexchanges.navvp.model.Result;

/**
 * Created by acer on 9/15/2017.
 */

public class Fragmentshop extends Fragment {
    JSONObject jsonobject;
    JSONArray jsonarray;

    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
     public static String NAME = "category_name";
    public  static String IMAGE = "icon_image";
    HashMap<String, String> map;
    ArrayList<Result> world;
    View view;
    ListView listview;
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new DownloadJSON().execute();
        view = inflater.inflate(R.layout.listview_main, container, false);
         listview = (ListView)view.findViewById(R.id.listview);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private class DownloadJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

       /* @Override
        protected Void doInBackground(Void... params) {
            try {
                Fragmentshop.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/login");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return LoginActivity.this.response;
        }*/
        @Override
        protected String doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
          //  world = new ArrayList<Result>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions.getJSONfromURL("http://honestshoppie.com/services/index.php/api/allcategories");

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("result");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    Log.d("map1","map1:"+map);
                    Log.d("jsonobject","jsonobject:"+jsonobject);

                   /* Result worldpop = new Result();

                    worldpop.setCategory_name(jsonobject.optString("category_name"));
                    worldpop.setIcon_image(jsonobject.optString("icon_image"));
                    world.add(worldpop);*/
                    // Retrive JSON Objects
                    if (jsonobject.has("category_name")) {
                        map.put("category_name", jsonobject.getString("category_name"));
                    }
                    if (jsonobject.has("icon_image")) {
                        map.put("icon_image", jsonobject.getString("icon_image"));
                    }
                     // Set the JSON Objects into the array
                    Log.d("arraylist","arraylist:"+arraylist);
                    arraylist.add(map);
                    Log.d("map","map:"+map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            // Locate the listview in listview_main.xml

            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(getActivity(), arraylist);
            Log.d("arraylist111111","arraylist:"+arraylist);
            Log.d("adapter11111111","adapter1111:"+adapter);
            // Set the adapter to the ListView


           try{
               if (adapter != null) {
                   Log.d("Adapter in Petopen", "is not null");
                   listview.setAdapter(adapter);
               } else {
                   Log.d("ADapter in petopen", "is null");
                   Toast.makeText(getActivity(),"NULL", Toast.LENGTH_LONG).show();
               }
           }catch(Exception e){
               Toast.makeText(getActivity(),"adapter:"+e.toString(), Toast.LENGTH_LONG).show();
           }

            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}

