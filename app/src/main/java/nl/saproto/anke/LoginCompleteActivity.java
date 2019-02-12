package nl.saproto.anke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.ClientSecretPost;
import net.openid.appauth.TokenResponse;

public class LoginCompleteActivity extends AppCompatActivity {

    public void onCreate(Bundle b) {
        super.onCreate(b);

        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());

        HelperProvider.readAuthState(getApplicationContext()).update(resp, ex);

        if (resp == null) {
            HelperProvider.showToast(getApplicationContext(), "Authentication cancelled!");
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }

        AuthorizationService authorizationService = new AuthorizationService(this);

        ClientAuthentication clientAuth = new ClientSecretPost("n9aKesBbxYAyV1oRyO6gPwo4nqF7CKtneubsEz6M");

        authorizationService.performTokenRequest(
                resp.createTokenExchangeRequest(),
                clientAuth,
                new AuthorizationService.TokenResponseCallback() {
                    @Override
                    public void onTokenRequestCompleted(TokenResponse resp, AuthorizationException ex) {
                        HelperProvider.readAuthState(getApplicationContext()).update(resp, ex);
                        if (resp != null) {
                            HelperProvider.showToast(getApplicationContext(), "Authentication finished!");
                        } else {
                            HelperProvider.showToast(getApplicationContext(), "Authentication failed!");
                        }
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    }
                });

    }

}
