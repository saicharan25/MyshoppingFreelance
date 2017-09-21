package info.devexchanges.navvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.home.MainActivity;


public class UnderConstractionActivity extends AppCompatActivity {

    class C02591 implements OnClickListener {
        C02591() {
        }

        public void onClick(View v) {
            UnderConstractionActivity.this.startActivity(new Intent(UnderConstractionActivity.this, MainActivity.class));
        }
    }

    class C02602 implements OnClickListener {
        C02602() {
        }

        public void onClick(View v) {
            UnderConstractionActivity.this.startActivity(new Intent(UnderConstractionActivity.this, Signup.class));
        }
    }

    class C02613 implements OnClickListener {
        C02613() {
        }

        public void onClick(View v) {
            UnderConstractionActivity.this.startActivity(new Intent(UnderConstractionActivity.this, ForgotPassword.class));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.login);
        LinearLayout register = (LinearLayout) findViewById(R.id.register);
        TextView forgotPasswordTv = (TextView) findViewById(R.id.forgot_password);
        ((Button) findViewById(R.id.login)).setOnClickListener(new C02591());
        register.setOnClickListener(new C02602());
        forgotPasswordTv.setOnClickListener(new C02613());
    }
}
