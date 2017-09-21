package info.devexchanges.navvp.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import info.devexchanges.navvp.R;
import info.devexchanges.navvp.Utils.Constants;
import info.devexchanges.navvp.services.PostData;

/*import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;*/

public class MyProfileActivity extends AppCompatActivity implements OnClickListener {
    private static final int STORAGE_PERMISSION_CODE = 123;
    final int DATE_PICKER_ID = R.id.et_dob;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Button btnProfileImage;
    Button btnUpdate;
    private Button buttonChoose;
    private Button buttonUpload;
    String cid;
    private CircleImageView civProfileImage;
    private int day;
    private EditText editText;
    EditText etAddress;
    EditText etCity;
    EditText etDob;
    EditText etGender;
    EditText etLocality;
    EditText etName;
    EditText etOTP;
    EditText etPincode;
    private Uri filePath;
    private ImageView imageView;
    private LinearLayout llProfileImage;
    Context mContext;
    private int month;
    private OnDateSetListener pickerListener = new C02544();
    String profileImageUrl;
    String response;
    private CardView view2;
    int year;

    class C02511 implements OnClickListener {
        C02511() {
        }

        public void onClick(View v) {
            MyProfileActivity.this.finish();
        }
    }

    class C02522 implements DialogInterface.OnClickListener {
        C02522() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
            MyProfileActivity.this.uploadMultipart();
            MyProfileActivity.this.finish();
        }
    }

    class C02533 implements DialogInterface.OnClickListener {
        C02533() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    class C02544 implements OnDateSetListener {
        C02544() {
        }

        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            MyProfileActivity.this.year = selectedYear;
            MyProfileActivity.this.month = selectedMonth;
            MyProfileActivity.this.day = selectedDay;
            MyProfileActivity.this.etDob.setText(new StringBuilder().append(MyProfileActivity.this.month + 1).append("-").append(MyProfileActivity.this.day).append("-").append(MyProfileActivity.this.year).append(StringUtils.SPACE));
        }
    }

    class MyAsyncTask2 extends AsyncTask<String, Void, String> {
        ProgressDialog asyncDialog;
        ArrayList paramsList = new ArrayList();
        String response;

        MyAsyncTask2() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.asyncDialog = new ProgressDialog(MyProfileActivity.this);
            this.asyncDialog.setMessage(MyProfileActivity.this.getString(R.string.msg_profile_loading));
            this.asyncDialog.show();
            this.response = "";
            this.paramsList.add(new BasicNameValuePair("cid", MyProfileActivity.this.cid));
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
                        Toast.makeText(MyProfileActivity.this.mContext, message, Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        JSONObject result = data.getJSONObject("result");
                        MyProfileActivity.this.etName.setText(result.getString("name"));
                        MyProfileActivity.this.etGender.setText(result.getString("gender"));
                        MyProfileActivity.this.etDob.setText(result.getString("dob").replace("\\/", "/"));
                        MyProfileActivity.this.etCity.setText(result.getString("city"));
                        MyProfileActivity.this.etPincode.setText(result.getString("postal_code"));
                        MyProfileActivity.this.etAddress.setText(result.getString("address"));
                        MyProfileActivity.this.profileImageUrl = result.getString("profile_image");
                        MyProfileActivity.this.profileImageUrl = Constants.HOST + MyProfileActivity.this.profileImageUrl.replace("https://espitha.com", "");
                        Picasso.with(MyProfileActivity.this.mContext).load(MyProfileActivity.this.profileImageUrl).error((int) R.drawable.common).into(MyProfileActivity.this.civProfileImage);
                        MyProfileActivity.this.hideSoftKeyboard();
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
        ProgressDialog asyncDialog;
        ArrayList paramsList = new ArrayList();

        MyAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            try {
                this.asyncDialog = new ProgressDialog(MyProfileActivity.this);
                this.asyncDialog.setMessage(MyProfileActivity.this.getString(R.string.msg_updating_profile));
                this.asyncDialog.show();
                MyProfileActivity.this.response = "";
                this.paramsList.add(new BasicNameValuePair("name", MyProfileActivity.this.etName.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("gender", MyProfileActivity.this.etGender.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("dob", MyProfileActivity.this.etDob.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("city", MyProfileActivity.this.etCity.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("localcity", MyProfileActivity.this.etLocality.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("pincode", MyProfileActivity.this.etPincode.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("address", MyProfileActivity.this.etAddress.getText().toString()));
                this.paramsList.add(new BasicNameValuePair("cid", "4OZM9SED"/*MyProfileActivity.this.cid*/));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPostExecute(String aDouble) {
            super.onPostExecute(aDouble);
            try {
                this.asyncDialog.dismiss();
                JSONObject data = new JSONObject(MyProfileActivity.this.response);
                int status = Integer.parseInt(data.getString("status"));
                String message = data.getString("message");
                switch (status) {
                    case 0:
                        Toast.makeText(MyProfileActivity.this.mContext, message, Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        MyProfileActivity.this.finish();
                        Toast.makeText(MyProfileActivity.this.mContext, "Profile successfully updated.", Toast.LENGTH_SHORT).show();
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
                MyProfileActivity.this.response = PostData.postData(this.paramsList, "http://honestshoppie.com/services/index.php/api/updateprofile");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return MyProfileActivity.this.response;
        }
    }

    public class MyEditTextDatePicker implements OnClickListener, OnDateSetListener {
        private int _birthYear;
        private Context _context;
        private int _day;
        EditText _editText;
        private int _month;

        public MyEditTextDatePicker(Context context, int editTextViewID) {
            this._editText = (EditText) ((Activity) context).findViewById(editTextViewID);
            this._editText.setOnClickListener(this);
            this._context = context;
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            this._birthYear = year;
            this._month = monthOfYear;
            this._day = dayOfMonth;
            updateDisplay();
        }

        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            new DatePickerDialog(this._context, this, calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)).show();
        }

        private void updateDisplay() {
            this._editText.setText(new StringBuilder().append(this._day).append("/").append(this._month + 1).append("/").append(this._birthYear).append(StringUtils.SPACE));
        }
    }

    private class selectableGender implements OnClickListener {
        public selectableGender(MyProfileActivity myProfileActivity, int et_gender) {
        }

        public void onClick(View v) {
            final Dialog dialog = new Dialog(MyProfileActivity.this);
            dialog.setContentView(R.layout.popup_gender);
            dialog.setTitle("This is my custom dialog box");
            dialog.setCancelable(false);
            final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.rg_gender);
            RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.rd_male);
            RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.rd_female);
            RadioButton rd3 = (RadioButton) dialog.findViewById(R.id.rd_other);
            String genderStr = MyProfileActivity.this.etGender.getText().toString();
            if (rd1.getText().toString().equalsIgnoreCase(genderStr)) {
                rd1.setChecked(true);
            } else if (rd2.getText().toString().equalsIgnoreCase(genderStr)) {
                rd2.setChecked(true);
            } else if (rd2.getText().toString().equalsIgnoreCase(genderStr)) {
                rd3.setChecked(true);
            } else {
                rd1.setChecked(true);
            }
            Button btnOk = (Button) dialog.findViewById(R.id.btn_gender);
            dialog.show();
            btnOk.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    MyProfileActivity.this.etGender.setText(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText());
                    dialog.dismiss();
                }
            });
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            hideSoftKeyboard();
            setContentView((int) R.layout.activity_profile_update);
            setTitle(getString(R.string.my_profile_update));
            this.cid = getIntent().getExtras().getString("cid");
            initData();
            ((TextView) findViewById(R.id.title)).setText(getTitle());
            ((ImageView) findViewById(R.id.backimage)).setOnClickListener(new C02511());
            this.etDob.setFocusable(false);
            this.etDob.setOnClickListener(new MyEditTextDatePicker(this, R.id.et_dob));
            this.etGender.setFocusable(false);
            this.etGender.setOnClickListener(new selectableGender(this, R.id.et_gender));
            this.btnUpdate.setOnClickListener(this);
            new MyAsyncTask2().execute(new String[]{""});
            requestStoragePermission();
            this.civProfileImage.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        this.mContext = getApplicationContext();
        this.etName = (EditText) findViewById(R.id.et_name);
        this.etGender = (EditText) findViewById(R.id.et_gender);
        this.etDob = (EditText) findViewById(R.id.et_dob);
        this.etCity = (EditText) findViewById(R.id.et_city);
        this.etLocality = (EditText) findViewById(R.id.et_locality);
        this.etPincode = (EditText) findViewById(R.id.et_pin_code);
        this.etAddress = (EditText) findViewById(R.id.et_address);
        this.btnUpdate = (Button) findViewById(R.id.btn_update);
       // this.view2 = (CardView) findViewById(R.id.view2);
        this.civProfileImage = (CircleImageView) findViewById(R.id.profile_image);
        hideSoftKeyboard();
    }

    public void onClick(View v) {
        if (v == this.buttonChoose) {
            showFileChooser();
        }
        if (v == this.buttonUpload) {
            uploadMultipart();
        }
        if (v == this.imageView) {
            showFileChooser();
        }
       /* if (v.getId() == R.id.view2) {
            showFileChooser();
        }*/
        if (v == this.btnUpdate) {
            Builder builder1 = new Builder(this);
            builder1.setMessage((CharSequence) "Are sure, You want to update your profile?");
            builder1.setCancelable(true);
            builder1.setPositiveButton((CharSequence) "Yes", new C02522());
            builder1.setNegativeButton((CharSequence) "No", new C02533());
            builder1.create().show();
        }
        if (v == this.btnProfileImage) {
            showFileChooser();
        }
        if (v == this.civProfileImage) {
            showFileChooser();
        }
    }

    public void uploadMultipart() {
        String path = getPath(this.filePath);
        try {
            if (path.isEmpty()) {
                new MyAsyncTask().execute(new String[0]);
                return;
            }
          /*  new MultipartUploadRequest(this, UUID.randomUUID().toString(), Constants.UPLOAD_URL)
                    .addFileToUpload(path, "photo").addParameter("name", this.etName.getText().toString())
                    .addParameter("gender", this.etGender.getText().toString())
                    .addParameter("dob", this.etDob.getText().toString()).addParameter("city", this.etCity.getText().toString())
                    .addParameter("localcity", this.etLocality.getText().toString()).addParameter("pincode", this.etPincode.getText().toString()).addParameter("address", this.etAddress.getText().toString())
                    .addParameter("cid", this.cid).setNotificationConfig(new UploadNotificationConfig()).setMaxRetries(2).startUpload();
        */
          finish();
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), this.PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.PICK_IMAGE_REQUEST && resultCode == -1 && data != null && data.getData() != null) {
            this.filePath = data.getData();
            try {
                this.bitmap = Media.getBitmap(getContentResolver(), this.filePath);
                this.civProfileImage.setImageBitmap(this.bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPath(Uri uri) {
        String path = "";
        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(Config.OTP_DELIMITER) + 1);
            cursor.close();
            cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "_id = ? ", new String[]{document_id}, null);
            cursor.moveToFirst();
            path = cursor.getString(cursor.getColumnIndex("_data"));
            cursor.close();
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return path;
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, STORAGE_PERMISSION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, STORAGE_PERMISSION_CODE);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != STORAGE_PERMISSION_CODE) {
            return;
        }
        if (grantResults.length <= 0 || grantResults[0] != 0) {
            Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectDate() {
        Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DATE);
        showDialog(R.id.et_dob);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case R.id.et_dob:
                return new DatePickerDialog(this, this.pickerListener, this.year, this.month, this.day);
            default:
                return null;
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
         //   ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
      //  inputMethodManager.showSoftInput(view, 0);
    }
}
