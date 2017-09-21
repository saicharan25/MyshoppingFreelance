package info.devexchanges.navvp.main;

import android.app.ProgressDialog;
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

import org.apache.commons.lang3.CharEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.ValidationsUtils;
import info.devexchanges.navvp.services.PostData;

public class Signup extends AppCompatActivity implements OnClickListener {
    static Signup ctx;
    public static Context mContext;
    String cid;
    EditText etDOB;
    EditText etEmail;
    EditText etGender;
    EditText etMobile;
    EditText etName;
    EditText etPassword;
    String otp;
    String response;
    Button signUpBtn;

    class C02571 implements OnClickListener {
        C02571() {
        }

        public void onClick(View v) {
            Signup.this.finish();
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        private ProgressDialog asyncDialog;
        ArrayList paramsList = new ArrayList();

        MyAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            try {
                this.asyncDialog = new ProgressDialog(Signup.this);
                this.asyncDialog.setMessage(Signup.this.getString(R.string.msg_signup));
                this.asyncDialog.show();
                Signup.this.response = "";
                this.paramsList.add(new BasicNameValuePair("name", Signup.this.etName.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("email", Signup.this.etEmail.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("password", Signup.this.etPassword.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("mobile", Signup.this.etMobile.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                this.asyncDialog.dismiss();
                JSONObject data = new JSONObject(Signup.this.response);
                int status = Integer.parseInt(data.getString("status"));
                String message = data.getString("message");
                Log.d("msg","msg: "+message);
                switch (status) {
                    case 0:
                        Toast.makeText(Signup.mContext, message, Toast.LENGTH_LONG).show();
                        return;
                    case 1:
                        JSONObject result = data.getJSONObject("result");
                        Log.d("otpverifymsg","otpmsg: "+message);
                        Intent intent = new Intent(Signup.mContext, OTPVerifyActivity.class);
                        intent.putExtra("cid", result.getString("cid"));
                        Log.d("otp","otp: "+result);
                        Log.d("otpverifymsg","otpmsg: "+message);
                        Signup.this.startActivity(intent);
                        Toast.makeText(Signup.mContext, message, Toast.LENGTH_SHORT).show();
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
                Signup.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/register");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Signup.this.response;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView((int) R.layout.register);
            initData();
            setTitle(getString(R.string.signup));
            ((TextView) findViewById(R.id.title)).setText(getTitle());
            ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new Signup());
            this.signUpBtn.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            mContext = getApplicationContext();
            this.etName = (EditText) findViewById(R.id.et_name);
            this.etEmail = (EditText) findViewById(R.id.et_email);
         //   this.etGender = (EditText) findViewById(R.id.et_gender);
            this.etMobile = (EditText) findViewById(R.id.et_mobile_no);
          //  this.etDOB = (EditText) findViewById(R.id.et_dob);
            this.etPassword = (EditText) findViewById(R.id.et_password);
            this.signUpBtn = (Button) findViewById(R.id.btn_sign_up);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
               // Toast.makeText(getApplicationContext(),"SIGNUP>>>>>>>>>",Toast.LENGTH_SHORT).show();
                if (ValidationsUtils.isValidField("Enter Name", this.etName, this) &&
                        ValidationsUtils.isValidEmail(this.etEmail, mContext, this) &&
                        ValidationsUtils.isValidPhone(this.etMobile, mContext, this) &&
                        ValidationsUtils.isValidPassword(this.etPassword, mContext, this)) {
                    new MyAsyncTask().execute(new String[0]);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public String postData2(String valueIWantToSend) {
        HttpClient httpclient = new DefaultHttpClient();
        String responseString = "";
        HttpPost httppost = new HttpPost("http://espitha.com/apserv/get_local_cities.php");
        try {
            List nameValuePairs = new ArrayList();
            nameValuePairs.add(new BasicNameValuePair("pincode", valueIWantToSend));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            Log.i("Tag", "postData: " + response);
            responseString = EntityUtils.toString(response.getEntity(), CharEncoding.UTF_8);
        } catch (ClientProtocolException e) {
        } catch (IOException e2) {
        }
        return responseString;
    }
}
