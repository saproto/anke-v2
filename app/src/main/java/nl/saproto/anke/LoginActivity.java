package nl.saproto.anke;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;

import nl.saproto.anke.OAuth.ProtoClient;

public class LoginActivity extends OAuthLoginActivity<ProtoClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onLoginSuccess() {
        HelperProvider.showToast(getApplicationContext(), "Authentication successful.");
        Intent userIntent = new Intent(getApplicationContext(), UserActivity.class);
        startActivity(userIntent);
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
