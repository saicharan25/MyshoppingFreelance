package info.devexchanges.navvp.Utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyBoardUtils {
    public static void hideSoftKeyboard(Activity act) {
        if (act.getCurrentFocus() != null) {
            ((InputMethodManager) act.getSystemService("input_method")).hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(View view, Activity act) {
        InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService("input_method");
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }
}
