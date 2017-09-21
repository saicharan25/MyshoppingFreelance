package info.devexchanges.navvp.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.MessageBoxUtils;
import info.devexchanges.navvp.home.MainActivity;
import info.devexchanges.navvp.model.Categories;
import info.devexchanges.navvp.services.PostData;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText etEmailNumber;
    EditText etPassword;
    TextInputLayout llEmailNumber;
    TextInputLayout llPassword;
    LinearLayout llRegister;
    Context mContext;
    String response;
    TextView tvForgotPassword;

    class C02431 implements DialogInterface.OnClickListener {
        C02431() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
            LoginActivity.this.finish();
            System.exit(0);
        }
    }

    class C02442 implements DialogInterface.OnClickListener {
        C02442() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    class C02453 implements DialogInterface.OnClickListener {
        C02453() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    class MyAsyncTask2 extends AsyncTask<String, Void, String> {
        ArrayList paramsList = new ArrayList();

        private class MyTextWatcher implements TextWatcher {
            private View view;

            private MyTextWatcher(View view) {
                this.view = view;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                switch (this.view.getId()) {
                    case R.id.input_email:
                        LoginActivity.this.validateEmail();
                        return;
                    case R.id.input_passowrd:
                        LoginActivity.this.validatePassword();
                        return;
                    case R.id.et_name:
                        LoginActivity.this.validateName();
                        return;
                    default:
                        return;
                }
            }
        }

        MyAsyncTask2() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
        }

        protected String doInBackground(String... params) {
            return LoginActivity.this.response;
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
                this.asyncDialog = new ProgressDialog(LoginActivity.this);
                this.asyncDialog.setMessage(LoginActivity.this.getString(R.string.msg_login));
                this.asyncDialog.show();
                LoginActivity.this.response = "";
                this.paramsList.add(new BasicNameValuePair("me", LoginActivity.this.etEmailNumber.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("password",  LoginActivity.this.etPassword.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                this.asyncDialog.dismiss();
                Categories c = (Categories) new Gson().fromJson(LoginActivity.this.response, Categories.class);
                LoginActivity.this.userLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected String doInBackground(String... params) {
            try {
                LoginActivity.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/login");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return LoginActivity.this.response;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView((int) R.layout.login);
            setTitle(getString(R.string.login));
            ((TextView) findViewById(R.id.title)).setText(getTitle());
            ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new LoginActivity());
            initData();
            this.btnLogin.setOnClickListener(this);
            this.llRegister.setOnClickListener(this);
            this.tvForgotPassword.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        this.llRegister = (LinearLayout) findViewById(R.id.register);
        this.llEmailNumber = (TextInputLayout) findViewById(R.id.input_layout_email);
        this.llPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        this.btnLogin = (Button) findViewById(R.id.btn_login);
        this.tvForgotPassword = (TextView) findViewById(R.id.forgot_password);
        this.etEmailNumber = (EditText) findViewById(R.id.input_email);
        this.etPassword = (EditText) findViewById(R.id.input_passowrd);
        this.mContext = getApplicationContext();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submitForm();
                return;
            case R.id.forgot_password:
                startActivity(new Intent(this, ForgotPassword.class));
                return;
            case R.id.register:
                startActivity(new Intent(this, Signup.class));
                return;
            default:
                return;
        }
    }

    private void submitForm() {
        if (validateEmail() && validatePassword()) {
            new MyAsyncTask().execute(new String[]{""});
        }
    }

    private boolean validateName() {
        if (!this.etEmailNumber.getText().toString().trim().isEmpty()) {
            return true;
        }
        this.etEmailNumber.setError(getString(R.string.err_msg_enter_email_no));
        requestFocus(this.etEmailNumber);
        return false;
    }

    private boolean validateEmail() {
        String email = this.etEmailNumber.getText().toString().trim();
        if (email.isEmpty()) {
            this.etEmailNumber.setError(getString(R.string.err_msg_enter_email_no));
            requestFocus(this.etEmailNumber);
            return false;
        } else if (isValidPhone(email)) {
            return true;
        } else {
            if (isValidEmail(email)) {
                return true;
            }
            this.etEmailNumber.setError(getString(R.string.err_msg_enter_email_no));
            requestFocus(this.etEmailNumber);
            return false;
        }
    }

    private boolean isValidPhone(String phone) {
        if (phone.length() == 10 && Long.valueOf(Long.parseLong(phone)).toString().length() == 10) {
            return true;
        }
        return false;
    }

    private boolean validatePassword() {
        if (!this.etPassword.getText().toString().trim().isEmpty()) {
            return true;
        }
        this.etPassword.setError(getString(R.string.err_msg_enter_password));
        requestFocus(this.etPassword);
        return false;
    }

    private static boolean isValidEmail(String email) {
        return TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(5);
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage((CharSequence) "Are sure, You want to Exit?");
        builder1.setCancelable(true);
        builder1.setPositiveButton((CharSequence) "Yes", new C02431());
        builder1.setNegativeButton((CharSequence) "No", new C02442());
        builder1.create().show();
    }

    private void userLogin() {
        try {
            JSONObject data = new JSONObject(this.response);
            int status = Integer.parseInt(data.getString("status"));
            String message = data.getString("message");
            switch (status) {
                case 0:
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage((CharSequence) "Please enter correct details");
                    dlgAlert.setTitle((CharSequence) "Login Error");
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    dlgAlert.setPositiveButton((CharSequence) "Ok", new C02453());
                    return;
                case 1:
                    JSONObject result = data.getJSONObject("result");
                    Intent intent = new Intent(this.mContext, WelcomeActivity.class);
                    intent.putExtra("cid", result.getString("cid"));
                    intent.putExtra("name", result.getString("name"));
                    intent.putExtra("balance", result.getString("balance"));
                    intent.putExtra("promo_balance", result.getString("promo_balance"));
                    intent.putExtra("profile_url", result.getString("profile_image"));
                    startActivity(intent);
                    Toast.makeText(this.mContext, "Successfully Login", Toast.LENGTH_LONG).show();
                    return;
                default:
                    return;
            }
        } catch (JSONException e) {
            MessageBoxUtils.createMessageBox("Error", "Login Failed", this);
            Toast.makeText(this.mContext, "Successfully Login", Toast.LENGTH_LONG).show();
            e.printStackTrace();

            MessageBoxUtils.createMessageBox("Error", "Login Failed", this);
            Toast.makeText(this.mContext, "Successfully Login", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

