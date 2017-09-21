package info.devexchanges.navvp.Utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import info.devexchanges.navvp.R;

public class ValidationsUtils {
    public static boolean isValidField(String msg, View v, Activity act) {
        EditText et = (EditText) v;
        if (!et.getText().toString().isEmpty() && !et.getText().toString().equalsIgnoreCase("null")) {
            return true;
        }
        et.setError(msg);
        requestFocus(v, act);
        return false;
    }

    public static boolean isValidateEmailNumber(View v, Context ctx, Activity act) {
        EditText etEmailNumber = (EditText) v;
        String email = etEmailNumber.getText().toString().trim();
        if (email.isEmpty()) {
            etEmailNumber.setError(ctx.getString(R.string.err_msg_enter_email_no));
            requestFocus(v, act);
            return false;
        } else if (isValidPhone(email)) {
            return true;
        } else {
            if (isValidEmail(email)) {
                return true;
            }
            etEmailNumber.setError(ctx.getString(R.string.err_msg_enter_email_no));
            requestFocus(v, act);
            return false;
        }
    }

    private static boolean isValidPhone(String phone) {
        if (phone.length() == 10 && Long.valueOf(Long.parseLong(phone)).toString().length() == 10) {
            return true;
        }
        return false;
    }

    private static boolean isValidEmail(String email) {
        return TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidEmail(EditText et, Context ctx, Activity act) {
        String email = et.getText().toString();
        if (TextUtils.isEmpty(email)) {
            et.setError(ctx.getString(R.string.err_msg_valid_email));
            requestFocus(et, act);
            return false;
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            et.setError(ctx.getString(R.string.err_msg_valid_email));
            requestFocus(et, act);
            return false;
        }
    }

    public static boolean isValidPhone(EditText et, Context ctx, Activity act) {
        String phone = et.getText().toString();
        if (!TextUtils.isEmpty(phone) && phone.length() == 10) {
            return true;
        }
        et.setError(ctx.getString(R.string.err_msg_valid_mobile_no));
        requestFocus(et, act);
        return false;
    }

    private static void requestFocus(View view, Activity act) {
        if (view.requestFocus()) {
            act.getWindow().setSoftInputMode(5);
        }
    }

    public static boolean isValidPassword(EditText etPassword, Context mContext, Activity act) {
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(mContext.getString(R.string.err_msg_enter_password));
            requestFocus(etPassword, act);
            return false;
        } else if (password.length() >= 6) {
            return true;
        } else {
            etPassword.setError(mContext.getString(R.string.err_msg_valid_password));
            requestFocus(etPassword, act);
            return false;
        }
    }
}
