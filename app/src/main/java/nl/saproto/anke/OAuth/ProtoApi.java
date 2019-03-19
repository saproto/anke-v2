package nl.saproto.anke.OAuth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class ProtoApi extends DefaultApi20 {

    private static final String AUTHORIZE_URL = "https://www.proto.utwente.nl/oauth/authorize";

    protected ProtoApi() {
    }

    private static class InstanceHolder {
        private static final ProtoApi INSTANCE = new ProtoApi();
    }

    public static ProtoApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.proto.utwente.nl/oauth/token";
    }

    @Override
    public String getAuthorizationBaseUrl() {
        return AUTHORIZE_URL;
    }

}
