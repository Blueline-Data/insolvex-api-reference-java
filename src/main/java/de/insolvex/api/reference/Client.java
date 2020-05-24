package de.insolvex.api.reference;

import de.insolvex.client.ApiClient;
import de.insolvex.client.Configuration;

public class Client {

    private static String getAccessToken() {
        String envToken = System.getenv("INSOLVEX_TOKEN");
        if  (envToken == null) {
            throw new RuntimeException("Access token needs to be provided via an environment variable INSOLVEX_TOKEN");
        }
        return envToken;
    }

    private static String getApiUrl() {
        String apiUrl = System.getenv("INSOLVEX_API_URL");
        if  (apiUrl == null) {
            return "https://api.insolvex.de";
        }
        else {
            return apiUrl;
        }
    }

    public static ApiClient getClient() {
        ApiClient client = Configuration.getDefaultApiClient();
        client.setAccessToken(getAccessToken());
        client.setBasePath(getApiUrl());
        return client;
    }

}
