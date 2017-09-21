package info.devexchanges.navvp.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import info.devexchanges.navvp.Utils.MessageBoxUtils;
import info.devexchanges.navvp.home.MainActivity;
import info.devexchanges.navvp.services.PostData;

public class OTPVerifyActivity extends AppCompatActivity {
    EditText etOTP;
    Context mContext;

    class C02561 implements OnClickListener {
        C02561() {
        }

        public void onClick(View v) {
            new MyAsyncTask().execute(new String[]{""});
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        private ProgressDialog asyncDialog;
        ArrayList paramsList = new ArrayList();
        String response;

        MyAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.asyncDialog = new ProgressDialog(OTPVerifyActivity.this);
            this.asyncDialog.setMessage(OTPVerifyActivity.this.getString(R.string.msg_verify_otp));
            this.asyncDialog.show();
            this.paramsList.add(new BasicNameValuePair("otp", OTPVerifyActivity.this.etOTP.getText().toString()));
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                this.asyncDialog.dismiss();
                JSONObject data = new JSONObject(this.response);
                int status = Integer.parseInt(data.getString("status"));
                String message = data.getString("message");
                switch (status) {
                    case 0:
                        MessageBoxUtils.createMessageBox("Error", "OTP Error retry", OTPVerifyActivity.this);
                        Toast.makeText(OTPVerifyActivity.this.mContext, message, Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        JSONObject result = data.getJSONObject("result");
                        Intent intent = new Intent(OTPVerifyActivity.this.mContext, MainActivity.class);
                        intent.putExtra("cid", result.getString("cid"));
                        OTPVerifyActivity.this.startActivity(intent);
                        Toast.makeText(OTPVerifyActivity.this.mContext, message, Toast.LENGTH_SHORT).show();
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
                this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/otpverify");
                return this.response;
            } catch (Exception e) {
                e.printStackTrace();
                return this.response;
            } catch (Throwable th) {
                return this.response;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView((int) R.layout.otp_verify);
            setTitle(getString(R.string.title_otp));
            ((TextView) findViewById(R.id.title)).setText(getTitle());
            ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new OTPVerifyActivity.C02561());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle EXTRAS = getIntent().getExtras();
        String cid = EXTRAS.getString("cid");
        String otp = EXTRAS.getString("otp");
        this.mContext = getApplicationContext();
        this.etOTP = (EditText) findViewById(R.id.et_otp);
        this.etOTP.setText(otp);
        ((Button) findViewById(R.id.btn_verify_otp)).setOnClickListener(new C02561());

    }
}
