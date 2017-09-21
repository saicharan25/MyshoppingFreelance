package info.devexchanges.navvp.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.home.MainActivity;


/**
 * Created by acer on 9/15/2017.
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static Button defaultFragment, argumentFragment;
    private static FragmentManager fragmentManager;
    ImageView iv1,iv2;
    Context mContext;
    public static String cid;
    private String name;
    private Double balance;
    private TextView promoBalanceTv;
    private String profile_url;
    private Double promoBalance;
    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        setTitle(getString(R.string.welcome));
        ((TextView) findViewById(R.id.title)).setText(getTitle());
        ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new WelcomeActivity());

        fragmentManager = getSupportFragmentManager();//Get Fragment Manager

        //Find Ids
        iv1 = (ImageView) findViewById(R.id.shopicon);
        iv2 = (ImageView) findViewById(R.id.foodicon);

        //Implement Click Listener

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);


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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopicon:

                //Replace default fragment
              //  fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new Fragmenthome()).commit();

                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("cid", cid);
                intent1.putExtra("name", this.name);
                intent1.putExtra("balance", this.balance);
                intent1.putExtra("promo_balance", this.promoBalance);
                intent1.putExtra("profile_url", this.profile_url);
                startActivity(intent1);

                break;
            case R.id.foodicon:

              /*  Fragment argumentFragment = new ArgumentFragment();//Get Fragment Instance
                Bundle data = new Bundle();//Use bundle to pass data
                data.putString("data", "This is Argument Fragment");//put string, int, etc in bundle with a key value
                argumentFragment.setArguments(data);//Finally set argument bundle to fragment

                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, argumentFragment).commit();//now replace the argument fragment
*/

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("cid", cid);
                intent.putExtra("name", this.name);
                intent.putExtra("balance", this.balance);
                intent.putExtra("promo_balance", this.promoBalance);
                intent.putExtra("profile_url", this.profile_url);
                startActivity(intent);

             //   fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new Fragment3()).commit();
                 break;
        }

    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
