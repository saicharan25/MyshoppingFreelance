package info.devexchanges.navvp.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.MessageBoxUtils;
import info.devexchanges.navvp.Utils.ValidationsUtils;
import info.devexchanges.navvp.services.PostData;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    Button btnForgotPassword;
    String cid;
    EditText etEmailMobile;
    EditText etNewPassword;
    Context mContext;
    String response;

    class C02411 implements View.OnClickListener {
        C02411() {
        }

        public void onClick(View v) {
            ForgotPassword.this.finish();
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog asyncDialog;
        ArrayList paramsList = new ArrayList();

        MyAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            try {
                this.asyncDialog = new ProgressDialog(ForgotPassword.this);
                this.asyncDialog.setMessage(ForgotPassword.this.getString(R.string.msg_forgot_password));
                this.asyncDialog.show();
                ForgotPassword.this.response = "";
                this.paramsList.add(new BasicNameValuePair("mobile_email", ForgotPassword.this.etEmailMobile.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                this.asyncDialog.dismiss();
                JSONObject data = new JSONObject(ForgotPassword.this.response);
                int status = Integer.parseInt(data.getString("status"));
                String message = data.getString("message");
                switch (status) {
                    case 0:
                        MessageBoxUtils.createMessageBox("Error", "Email/Mobile not found.", ForgotPassword.this);
                        Toast.makeText(ForgotPassword.this.mContext, "Email/Mobile not found.", Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        Toast.makeText(ForgotPassword.this.mContext, message, Toast.LENGTH_SHORT).show();
                        ForgotPassword.this.finish();
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
                ForgotPassword.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/forgot_password");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ForgotPassword.this.response;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView((int) R.layout.forgot);
        setTitle(getString(R.string.title_forgot_password));
        ((TextView) findViewById(R.id.title)).setText(getTitle());
        ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new ForgotPassword());
        initData();
        this.btnForgotPassword.setOnClickListener(this);

    }

    protected void onResume() {
        super.onResume();
    }

    public void initData() {
        this.mContext = getApplicationContext();
        this.etEmailMobile = (EditText) findViewById(R.id.et_email_mobile);
        this.btnForgotPassword = (Button) findViewById(R.id.btn_forgot_password);
    }

    public void onClick(View v) {
        if (ValidationsUtils.isValidateEmailNumber(this.etEmailMobile, this.mContext, this)) {
            new MyAsyncTask().execute(new String[]{""});
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
