package nl.saproto.anke;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import nl.saproto.anke.OAuth.ProtoClient;

public class UserActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ProtoClient client = new ProtoClient(getApplicationContext());
        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                HelperProvider.showToast(getApplicationContext(), "Welcome!");
                System.out.println("Test");
            }
        });
    }

}
