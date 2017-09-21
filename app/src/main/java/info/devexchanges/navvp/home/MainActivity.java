package info.devexchanges.navvp.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.Constants;
import info.devexchanges.navvp.adapter.ViewPagerAdapter;
import info.devexchanges.navvp.main.ChangePasswordActivity;
import info.devexchanges.navvp.main.MyProfileActivity;
import info.devexchanges.navvp.model.Categories;
import info.devexchanges.navvp.model.Category;
import info.devexchanges.navvp.services.PostData;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Shopping", "Food & Drinks", "Local Services"};
    public static Categories categories;
    public static List<Category> categoryList;
    public static String cid;
    public static Context mContext;
    private Double balance;
    private String[] items;
    private String mActivityTitle;
    SimpleAdapter mAdapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mDrawerTitles;
    private ActionBarDrawerToggle mDrawerToggle;
    private ExpandableListAdapter mExpandableListAdapter;
    private Map<String, List<String>> mExpandableListData;
    private List<String> mExpandableListTitle;
    private ExpandableListView mExpandableListView;
    private ListView mListView;
    private String name;
    private ImageView profile;
    private TextView balanceTv;
    private TextView promoBalanceTv;
    private String profile_url;
    private Double promoBalance;
    Bundle savedInstanceState;
    private String response;


    class C02431 implements DialogInterface.OnClickListener {
        C02431() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
            MainActivity.this.finish();
            System.exit(0);
        }
    }


    class MyAsyncTask2 extends AsyncTask<String, Void, String> {
        ArrayList paramsList = new ArrayList();
        String response;

        MyAsyncTask2() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.response = "";
            this.paramsList.add(new BasicNameValuePair("cid", MainActivity.cid));
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                JSONObject data = new JSONObject(this.response);
                int status = Integer.parseInt(data.getString("status"));
                String message = data.getString("message");
                switch (status) {
                    case 0:
                        Toast.makeText(MainActivity.mContext, message, Toast.LENGTH_LONG).show();
                        return;
                    case 1:
                        String str;
                        JSONObject result = data.getJSONObject("result");
                        MainActivity.this.name = result.getString("name");
                        MainActivity.this.promoBalance = Double.valueOf(Double.parseDouble(result.getString("promo_balance").isEmpty() ? "0.0" : result.getString("promo_balance")));
                        MainActivity mainActivity = MainActivity.this;
                        if (result.getString("balance").isEmpty()) {
                            str = "0.0";
                        } else {
                            str = result.getString("balance");
                        }
                        mainActivity.balance = Double.valueOf(Double.parseDouble(str));
                        MainActivity.this.profile_url = result.getString("profile_image");
                        MainActivity.this.profile_url = Constants.HOST + MainActivity.this.profile_url.replace("https://espitha.com", "");
                        Picasso.with(MainActivity.mContext).load(MainActivity.this.profile_url).error((int) R.drawable.common).into(MainActivity.this.profile);
                        MainActivity.this.balanceTv.setText(MainActivity.this.balance.toString());
                        MainActivity.this.promoBalanceTv.setText(MainActivity.this.promoBalance.toString());
                      return;
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected String doInBackground(String... params) {
            try {
                this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/getprofile");
                return this.response;
            } catch (Exception e) {
                e.printStackTrace();
                return this.response;
            } catch (Throwable th) {
                return this.response;
            }
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        ArrayList paramsList = new ArrayList();

        MyAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            try {
                MainActivity.this.response = "";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                MainActivity.categories = (Categories) new Gson().fromJson(MainActivity.this.response, Categories.class);
                MainActivity.categoryList = MainActivity.categories.getCategories();
                if (MainActivity.this.savedInstanceState == null) {
                    MainActivity.this.selectFirstItemAsDefault();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        protected String doInBackground(String... params) {
            try {
                MainActivity.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/allcategories");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return MainActivity.this.response;
        }
    }

    private void selectFirstItemAsDefault() {
      /*  if (mNavigationManager != null) {
            String firstActionMovie = getResources().getStringArray(R.array.comedies)[0];
            mNavigationManager.showFragmentComedy(categories);
        }*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        CircleImageView cv = (CircleImageView) findViewById(R.id.circleView);
        setSupportActionBar(toolbar);

        this.mActivityTitle = getTitle().toString();
        this.mDrawerTitles = getResources().getStringArray(R.array.navigation_menu_items);
        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);

        this.savedInstanceState = savedInstanceState;
        Bundle EXTRAS = getIntent().getExtras();
        try
        {
            double d;
            cid = EXTRAS.getString("cid");
        this.name = EXTRAS.getString("name");
        String str = EXTRAS.getString("balance");
        this.balance = Double.valueOf(str.isEmpty() ? 0.0d : Double.valueOf(str).doubleValue());
        str = EXTRAS.getString("promo_balance");
            if (str.isEmpty()) {
                d = 0.0d;
            } else {
                d = Double.valueOf(str).doubleValue();
            }
            this.promoBalance = Double.valueOf(d);
        }catch(NullPointerException npe){

        }catch(NumberFormatException nfe){

        }catch(Exception e){

        }
        this.profile_url = EXTRAS.getString("profile_url");
        new MyAsyncTask().execute(new String[0]);
        this.mActivityTitle = getTitle().toString();
        this.mDrawerTitles = getResources().getStringArray(R.array.navigation_menu_items);

        ((TextView) listHeaderView.findViewById(R.id.et_name)).setText(this.name);
       // Picasso.with(mContext).load(this.profile_url).error((int) R.drawable.common).into(this.profile);
        this.balanceTv = (TextView) listHeaderView.findViewById(R.id.tv_balance);
       // this.balanceTv.setText(this.balance.toString());
        this.promoBalanceTv = (TextView) listHeaderView.findViewById(R.id.tv_promo);
      //  this.promoBalanceTv.setText(this.promoBalance.toString());

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        new MyAsyncTask2().execute(new String[0]);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
      //  tabLayout = (TabLayout) findViewById(R.id.tab_layout);
       /* for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }
        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.addHeaderView(listHeaderView);

        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //change ViewPager page when tab selected
       /* tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fr1) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.fr2) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.fr3) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.profile) {
            Intent intent = new Intent(this, MyProfileActivity.class);
            intent.putExtra("string", "Go to other Activity by NavigationView item cliked!");
            startActivity(intent);
        } else if (id == R.id.logout) {
            finish();
        } else if (id == R.id.changepassword) {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            intent.putExtra("string", "Go to other Activity by NavigationView item cliked!");
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onCreateOptionsMenu(menu);
        //  getMenuInflater().inflate(R.menu.menu_main, menu);
        // return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage((CharSequence) "Are sure, You want to Exit?");
        builder1.setCancelable(true);
        builder1.setPositiveButton((CharSequence) "Yes", new MainActivity.C02431());
        builder1.setNegativeButton((CharSequence) "No", new MainActivity.C02431());
        builder1.create().show();
    }
}

