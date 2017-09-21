package info.devexchanges.navvp.Utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;

public class MessageBoxUtils {

    static class C02801 implements OnClickListener {
        C02801() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    public static void createMessageBox(String title, String message, Activity act) {
        Builder dlgAlert = new Builder(act);
        dlgAlert.setMessage((CharSequence) message);
        dlgAlert.setTitle((CharSequence) title);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        dlgAlert.setPositiveButton((CharSequence) "Ok", new C02801());
    }
}
