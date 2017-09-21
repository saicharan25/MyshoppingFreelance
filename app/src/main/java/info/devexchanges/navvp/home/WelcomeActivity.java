package info.devexchanges.navvp.home;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import info.devexchanges.navvp.R;


/**
 * Created by acer on 9/15/2017.
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static Button defaultFragment, argumentFragment;
    ImageView iv1,iv2;
    TextView tv1,tv2;
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

        //Find Ids
        iv1 = (ImageView) findViewById(R.id.shopicon);
        iv2 = (ImageView) findViewById(R.id.foodicon);

        tv1 = (TextView) findViewById(R.id.gotoshop);
        tv2 = (TextView) findViewById(R.id.gotofood);
        //Implement Click Listener

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);

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

                Toast.makeText(getApplicationContext(),"Food click",Toast.LENGTH_LONG).show();

                  break;
            case R.id.gotoshop:

                Intent intent3 = new Intent(this, MainActivity.class);
                intent3.putExtra("cid", cid);
                intent3.putExtra("name", this.name);
                intent3.putExtra("balance", this.balance);
                intent3.putExtra("promo_balance", this.promoBalance);
                intent3.putExtra("profile_url", this.profile_url);
                startActivity(intent3);

                break;
            case R.id.gotofood:

                Toast.makeText(getApplicationContext(),"Food text click",Toast.LENGTH_LONG).show();

                break;
        }

    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
