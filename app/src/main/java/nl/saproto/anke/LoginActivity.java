package nl.saproto.anke;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.startOauthFlow();
    }

    protected void startOauthFlow() {

        // Feedback for the user
        HelperProvider.showToast(getApplicationContext(), "Redirecting to the S.A. Proto website for authentication...");

        // Starting OAuth flow
        AuthorizationServiceConfiguration serviceConfiguration =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://www.proto.utwente.nl/oauth/authorize"),
                        Uri.parse("https://www.proto.utwente.nl/oauth/token"));

        HelperProvider.writeAuthState(getApplicationContext(), new AuthState(serviceConfiguration));

        AuthorizationRequest.Builder authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfiguration,
                        "2",
                        ResponseTypeValues.CODE,
                        Uri.parse("saproto://oauth_callback"));

        AuthorizationService authorizationService = new AuthorizationService(this);

        authorizationService.performAuthorizationRequest(authRequestBuilder.build(),
                PendingIntent.getActivity(this, 0, new Intent(this, LoginCompleteActivity.class), 0),
                PendingIntent.getActivity(this, 0, new Intent(this, LoginCompleteActivity.class), 0));

    }

}
