package nl.saproto.anke;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Catch incoming OAuth2 response
        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            this.finishOauthFlow(data);
        } else {
            this.startOauthFlow();
        }
    }

    protected void startOauthFlow() {

        // Feedback for the user
        HelperProvider.showToast(getApplicationContext(), "Redirecting to the S.A. Proto website for authentication...");

        // Open webview for OAuth2
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApiProvider.getOauthPath()));
        startActivity(browserIntent);

    }

    protected void finishOauthFlow(Uri callbackData) {

        // Feedback for the user
        HelperProvider.showToast(getApplicationContext(), "Processing authentication response...");

        // Test and save credentials
        String token = callbackData.toString().replace("saproto://oauth_callback?code=", "");
        ApiProvider.saveOauthToken(getApplicationContext(), token);

        // Return to main activity
        finish();

    }

}
