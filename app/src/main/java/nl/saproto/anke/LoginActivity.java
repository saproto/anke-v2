package nl.saproto.anke;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cz.msebera.android.httpclient.Header;
import nl.saproto.anke.Database.AppDatabase;
import nl.saproto.anke.Database.User;
import nl.saproto.anke.OAuth.ProtoClient;

public class LoginActivity extends OAuthLoginActivity<ProtoClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLoginSuccess() {

        ProtoClient client = new ProtoClient(getApplicationContext());
        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                try {
                    final AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

                    final User user = new User();
                    user.calling_name = json.getString("calling_name");
                    user.name = json.getString("name");

                    Executor myExecutor = Executors.newSingleThreadExecutor();
                    myExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.userModel().insertUser(user);
                        }
                    });

                    HelperProvider.showToast(getApplicationContext(), "Authentication successful.");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    // Fires if the authentication process fails for any reason.
    @Override
    public void onLoginFailure(Exception e) {
        HelperProvider.showToast(getApplicationContext(), "Authentication failed.");
    }

    public void loginToRest(View view) {
        HelperProvider.showToast(getApplicationContext(), "Authenticating with S.A. Proto...");
        getClient().connect();
    }

}
