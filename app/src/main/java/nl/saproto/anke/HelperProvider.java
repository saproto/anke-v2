package nl.saproto.anke;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import net.openid.appauth.AuthState;

import org.json.JSONException;

public class HelperProvider {
    private CoordinatorLayout coordinatorLayout;

    public static void showToast(Context context, String message) {

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public static AuthState readAuthState(Context context) {
        SharedPreferences authPrefs = context.getSharedPreferences("auth", 0);
        String stateJson = authPrefs.getString("stateJson", null);
        if (stateJson != null) {
            try {
                return AuthState.jsonDeserialize(stateJson);
            } catch (JSONException e) {
                HelperProvider.showToast(context, "Invalid AuthState in Shared Preferences.");
                return new AuthState();
            }
        } else {
            return new AuthState();
        }
    }

    public static void writeAuthState(Context context, AuthState state) {
        SharedPreferences authPrefs = context.getSharedPreferences("auth", 0);
        authPrefs.edit()
                .putString("stateJson", state.jsonSerializeString())
                .apply();
    }

}
