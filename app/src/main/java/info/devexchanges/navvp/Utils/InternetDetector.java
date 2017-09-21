package info.devexchanges.navvp.Utils;

/**
 * Created by naresh on 04/07/2016.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import info.devexchanges.navvp.R;


public class InternetDetector {

    public static boolean isConnected(final Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

       /*     final int version = Build.VERSION.SDK_INT;
            if (version >= 23) {

            }
            else{

            }*/
//old code
           /* NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }*/

//new code
            NetworkInfo info=connectivity.getActiveNetworkInfo();
            if (info != null){
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
               // return true;
            }
        }
        ShowDialog.displayDialog(context,context.getString(R.string.app_name), "Oops ..! check your Internet connection and try again ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(
                        Settings.ACTION_SETTINGS));
            }
        },"Turn ON",null, null);


       // showAlertDialog(context, "Oops ..! check your Internet connection and try again ", false);
        return false;
    }


    public static void showAlertDialog(final Context context, String message, Boolean status) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(R.string.app_name);

        // Setting Dialog Message
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.hslogo : R.drawable.hslogo);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Turn ON", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {

                context.startActivity(new Intent(
                        Settings.ACTION_SETTINGS));

            }
        });
        // Showing Alert Message
        alertDialog.show();
    }


    public static boolean isGpsAvailable(Context context) {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            showGPSDisabledAlertToUser(context);
            return false;
        }
    }

    public static void showGPSDisabledAlertToUser(final Context context) {

        /*ShowDialog.displayDialog(context,
                context.getString(R.string.app_name),
                "GPS is disabled in your device. Would you like to enable it?", new1 Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,
                        "Please set Mode to High Accuracy.",
                        Toast.LENGTH_LONG).show();
                Intent callGPSSettingIntent = new1 Intent(
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(callGPSSettingIntent);
            }
        }, "Yes", new1 DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, "No");*/


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setIcon(R.drawable.hslogo);
        alertDialogBuilder
                .setMessage(
                        "GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(context,
                                        "Please set Mode to High Accuracy.",
                                        Toast.LENGTH_LONG).show();
                                Intent callGPSSettingIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                context.startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();


       /* Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getColor(context,0xff0000));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getColor(context,0xff0000));*/


        alert.show();
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}


