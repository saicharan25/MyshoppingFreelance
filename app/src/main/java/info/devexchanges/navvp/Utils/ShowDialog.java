package info.devexchanges.navvp.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import info.devexchanges.navvp.R;


/**
 * Created by Android01 on 5/17/2016.
 */
public class ShowDialog {


   /* public static Dialog displayDialog(Context applicationContext, String title, String msg,
                                    DialogInterface.OnClickListener listener, String buttonPositive,DialogInterface.OnClickListener listener1,String buttonNegative) {

       // AlertDialog.Builder mAlertDialog = new1 AlertDialog.Builder(new1 ContextThemeWrapper(applicationContext, R.style.AlertDialogCustom));
        AlertDialog.Builder mAlertDialog = new1 AlertDialog.Builder(applicationContext);
        mAlertDialog.setIcon(R.drawable.logo);
        mAlertDialog.setTitle(title);
        mAlertDialog.setMessage(msg);
        mAlertDialog.setPositiveButton(buttonPositive, listener);
        mAlertDialog.setCancelable(false);
        mAlertDialog.setNegativeButton(buttonNegative, listener1);
        Dialog dialog = mAlertDialog.create();
        dialog.show();

       *//* Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLACK);
        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);*//*

        return dialog;
    }*/

    public static Dialog displayDialog(Context applicationContext, String title, String msg,
                                       DialogInterface.OnClickListener listener, String buttonPositive, DialogInterface.OnClickListener listener1, String buttonNegative) {

        // AlertDialog.Builder mAlertDialog = new1 AlertDialog.Builder(new1 ContextThemeWrapper(applicationContext, R.style.AlertDialogCustom));
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(applicationContext);
        mAlertDialog.setIcon(R.drawable.hslogo);
        mAlertDialog.setTitle(title);
        mAlertDialog.setMessage(msg);
        mAlertDialog.setPositiveButton(buttonPositive, listener);
        mAlertDialog.setCancelable(false);
        mAlertDialog.setNegativeButton(buttonNegative, listener1);
        AlertDialog dialog = mAlertDialog.create();
        dialog.show();

        Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getColor(applicationContext, R.color.colorPrimary));
        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getColor(applicationContext, R.color.colorPrimary));

        return dialog;
    }

/*
    public static void showSnackBar(View v, String msg) {
        Snackbar bar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        bar.show();

      *//*  Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Had a snack at Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Undo", mOnClickListener);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();*//*
    }*/

    public static void showToast(String message, Context context) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static final Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getDrawable(context, id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

   /* public static Toolbar getToolBar(String title, final AppCompatActivity cc) {
        Toolbar mToolbar = (Toolbar) cc.findViewById(R.id.toolbar_plain);
        cc.setSupportActionBar(mToolbar);
        cc.getSupportActionBar().setTitle(title);
        //   mToolbar.setTitleTextAppearance(getApplicationContext(),text);
        //getApplicationContext().getDrawable(R.drawable.abc_ic_ab_back_material);
        final Drawable upArrow = cc.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        if (upArrow != null) {
            upArrow.setColorFilter(ContextCompat.getColor(cc, R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        cc.getSupportActionBar().setHomeAsUpIndicator(upArrow);
        mToolbar.setNavigationIcon(upArrow);
        return mToolbar;
    }
*/
    public static ProgressDialog showProgress(Context context) {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getString(R.string.progress_msg));
        pDialog.setCancelable(false);
        // pDialog.setProgressStyle(R.style.ProgressBar);
        // pDialog.get.setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        //pDialog.setProgressStyle(Pr)
        return pDialog;
    }


    public static void setImage(String url, ImageView imageView, Context context) {
        Picasso.with(context).load(url).into(imageView);

    }


    public static void hideKeyboad(final Context con, final EditText et) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do your stuff here
                    InputMethodManager imm1 = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.hideSoftInputFromWindow(et.getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    public static void setFilters(final Context con, final EditText et) {
        et.setFilters(new InputFilter[]{
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if (src.equals("")) { // for backspace
                            return src;
                        }
                        if (src.toString().matches("[\\x00-\\x7F]+")) {
                            return src;
                        }
                        return "";
                    }
                }
        });
    }



/*    public static Spanned setHtmlText(String name) {

        //Html styles
*//*        public static final int FROM_HTML_MODE_COMPACT = 63;
        public static final int FROM_HTML_MODE_LEGACY = 0;
        public static final int FROM_HTML_OPTION_USE_CSS_COLORS = 256;
        public static final int FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE = 32;
        public static final int FROM_HTML_SEPARATOR_LINE_BREAK_DIV = 16;
        public static final int FROM_HTML_SEPARATOR_LINE_BREAK_HEADING = 2;
        public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST = 8;
        public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM = 4;
        public static final int FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH = 1;
        public static final int TO_HTML_PARAGRAPH_LINES_CONSECUTIVE = 0;
        public static final int TO_HTML_PARAGRAPH_LINES_INDIVIDUAL = 1;*//*

        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(name, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(name);
        }
        return result;
    }*/
/*public static boolean isSameDay(String str_weekdate1) {
    SimpleDateFormat format = new1 SimpleDateFormat("MM/dd/yyyy");
    boolean isToday = false;
    Date dtoday = new1 Date();
    String stoday;
    SimpleDateFormat dateOnly = new1 SimpleDateFormat("MM/dd/yyyy");
    stoday = dateOnly.format(dtoday);
    try {
        Date deventDate = format.parse(str_weekdate1);
        String seventDate = dateOnly.format(deventDate);

        System.out.println(seventDate + " = " + stoday);

        if(stoday.equals(seventDate)){
            isToday = true;
        }
        System.out.println(" Status " + isToday);
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return isToday;
}*/


    public static void setImagePicasso(String imageurl, ImageView imageView, Context con) {

        Picasso.with(con)
                .load(imageurl)
                .placeholder(R.drawable.hslogo)
                .error(R.drawable.hslogo)
                .into(imageView);
    }

    public static void setImagePicassos(String imageurl, ImageView imageView, Context con) {
//.placeholder(R.drawable.screen_bg)
        Picasso.with(con)
                .load(imageurl)

                .error(R.drawable.hslogo)
                .into(imageView);
    }

    //isTabletDevice(activity)

    private static boolean isTabletDevice(Context activityContext) {

        boolean device_large = ((activityContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);

        DisplayMetrics metrics = new DisplayMetrics();
        Activity activity = (Activity) activityContext;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (device_large) {
            //Tablet
            if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_TV) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_HIGH) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_280) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_400) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_XXHIGH) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_560) {
                return true;
            } else if (metrics.densityDpi == DisplayMetrics.DENSITY_XXXHIGH) {
                return true;
            }
        } else {
            //Mobile
        }
        return false;
    }

    public static void setvalidationMsg(Context con, String message) {

        ShowDialog.displayDialog(con, con.getString(R.string.app_name), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        }, "OK", null, null);
    }
    public static String getToken(Context con) {

        SharedPreferences sh_login = con.getSharedPreferences(con.getString(R.string.sh_login), Context.MODE_PRIVATE);
        String str_token = sh_login.getString(con.getString(R.string.sh_logintoken), "");

        return str_token;
    }


}