package nl.saproto.anke.OAuth;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ProtoClient extends OAuthBaseClient {
    public static final BaseApi REST_API_INSTANCE = ProtoApi.instance();
    public static final String REST_URL = "https://www.proto.utwente.nl/api";
    public static final String REST_CONSUMER_KEY = "2";
    public static final String REST_CONSUMER_SECRET = "n9aKesBbxYAyV1oRyO6gPwo4nqF7CKtneubsEz6M";
    public static final String REST_CALLBACK_URL = "saproto://oauth_callback";

    public ProtoClient(Context context) {
        super(context, REST_API_INSTANCE, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getUserInfo(AsyncHttpResponseHandler handler) {
        client.get(getApiUrl("user/info"), new RequestParams(), handler);
    }
}
