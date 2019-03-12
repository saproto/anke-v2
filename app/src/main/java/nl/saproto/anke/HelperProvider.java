package nl.saproto.anke;

import android.content.Context;
import android.widget.Toast;

public class HelperProvider {

    public static void showToast(Context context, String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
