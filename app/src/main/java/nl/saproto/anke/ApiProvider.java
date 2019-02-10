package nl.saproto.anke;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputContentInfo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class ApiProvider {

    private static final String BASE_PATH = "https://www.proto.utwente.nl/";

    private static final String CLIENT_ID = "2";
    private static final String CLIENT_SECRET = "n9aKesBbxYAyV1oRyO6gPwo4nqF7CKtneubsEz6M";

    private static final String OAUTH_PREFERENCES = "saproto_oauth";


    private static void commitOauthToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ApiProvider.OAUTH_PREFERENCES, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString("oauth_token", token);
        sharedPreferencesEditor.commit();
    }

    private static String getOauthToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ApiProvider.OAUTH_PREFERENCES, 0);
        return sharedPreferences.getString("oauth_token", "");
    }

    public static boolean saveOauthToken(Context context, String token) {
        ApiProvider.commitOauthToken(context, token);
        String credentialCheck = ApiProvider.getApiData(context, "api/user/info");
        HelperProvider.showToast(context, ApiProvider.getOauthToken(context));
        return true;
    }

    public static String getOauthPath() {
        // TODO Niet mooi, moet eigenlijk met een implode, maar dat mocht van Marloes niet nu. Wel terecht stiekem.
        return "https://www.proto.utwente.nl/oauth/authorize?" +
                "client_id=" + ApiProvider.CLIENT_ID + "&" +
                "response_type=code" + "&" +
                "scope=%2A" + "&" + // *
                "redirect_uri=saproto%3A%2F%2Foauth_callback"; // saproto://oauth_callback
    }

    public static String getApiData(Context context, String path) {
        return ApiProvider.getApiData(context, path, (new String[0]));
    }

    public static String getApiData(Context context, String path, String[] parameters) {
        try {

            URL url = new URL(BASE_PATH + path);
            URLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("client_id", ApiProvider.CLIENT_ID);
            connection.addRequestProperty("client_secret", ApiProvider.CLIENT_SECRET);
            connection.addRequestProperty("Authorization", "OAuth " + ApiProvider.getOauthToken(context));

            InputStreamReader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));

            int data = 0;
            String response = "";

            while (data != 0) {
                data = in.read();
                response += (char) data;
            }

            return response;

        } catch (IOException e) {
            HelperProvider.showToast(context, "Something went wrong retrieving data.");
        } finally {
            return null;
        }
    }

}
