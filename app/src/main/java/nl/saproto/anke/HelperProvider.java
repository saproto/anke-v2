package nl.saproto.anke;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import android.widget.Toast;

public class HelperProvider {
    private CoordinatorLayout coordinatorLayout;

    public static void showToast(Context context, String message) {

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
