package info.devexchanges.navvp.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    static Toast mToast;

    public static void makeToast(Context ctx, String msg, int duration) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(ctx, msg, duration);
        mToast.show();
    }
}
