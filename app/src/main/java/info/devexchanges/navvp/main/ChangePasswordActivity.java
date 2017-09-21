package info.devexchanges.navvp.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.services.PostData;

public class ChangePasswordActivity extends AppCompatActivity implements OnClickListener {
    Button btnChangePassword;
    String cid;
    EditText etNewPassword;
    EditText etOldPassword;
    Context mContext;
    String response;

    class C02401 implements OnClickListener {
        C02401() {
        }

        public void onClick(View v) {
            ChangePasswordActivity.this.finish();
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        ArrayList paramsList = new ArrayList();

        MyAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            try {
                ChangePasswordActivity.this.response = "";
                this.paramsList.add(new BasicNameValuePair("npass", ChangePasswordActivity.this.etNewPassword.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("cpass", ChangePasswordActivity.this.etOldPassword.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("cid", "4OZM9SED"/*ChangePasswordActivity.this.cid*/));
                Log.d("cid","cid: "+cid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                JSONObject data = new JSONObject(ChangePasswordActivity.this.response);
                int status = Integer.parseInt(data.getString("status"));
                String message = data.getString("message");
                switch (status) {
                    case 0:
                        Toast.makeText(ChangePasswordActivity.this.mContext, message, Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        ChangePasswordActivity.this.finish();
                        ChangePasswordActivity.this.startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                        Toast.makeText(ChangePasswordActivity.this.mContext, message, Toast.LENGTH_SHORT).show();
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
                ChangePasswordActivity.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/changepassword");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ChangePasswordActivity.this.response;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView((int) R.layout.activity_change_password);
            setTitle(getString(R.string.title_change_password));
            ((TextView) findViewById(R.id.title)).setText(getTitle());
            ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new C02401());
            this.cid = getIntent().getExtras().getString("cid");
            Log.d("ciddd","cidddd: "+getIntent().getExtras().getString("cid"));
            initData();
            this.btnChangePassword.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        this.mContext = getApplicationContext();
        this.etOldPassword = (EditText) findViewById(R.id.et_old_password);
        this.etNewPassword = (EditText) findViewById(R.id.et_new_password);
        this.btnChangePassword = (Button) findViewById(R.id.btn_change_password);
    }

    public void onClick(View v) {
        new MyAsyncTask().execute(new String[]{""});
    }
}
