package HWS;


import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

import java.io.IOException;

import okhttp3.OkHttpClient;

import static java.util.concurrent.TimeUnit.SECONDS;

class JavaCoreSix {


    OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .connectTimeout(30, SECONDS)
            .readTimeout(30, SECONDS)
            .writeTimeout(30, SECONDS)
            .followRedirects(true)
            .retryOnConnectionFailure(true)
            .build();


    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String LOCATIONS = "locations";
    private static final String API_VERSION = "v1";
    private static final String TOP_CITIES = "topcities";
    private static final String CITIES_NUMBER = "50";

    private static final String SAINT_PETERSBURG_KEY = "474212_PC";
    private static final String API_KEY = "Q4qHoU4e8qRqMWY8qGPGtumuAVv9GNQD";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/locations/v1/cities/search}")
                .build();

        Response response = client.newCall(request).execute();

        String body = response.body().string();

        client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(API_VERSION)
                .addPathSegment(TOP_CITIES)
                .addPathSegment(CITIES_NUMBER)
                .addQueryParameter("apikay", API_KEY)
                .addQueryParameter("language", "en-us")
                .build();

        System.out.println(url.toString());

        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        String jsonResponse = client.newCall(requesthttp).execute().body().string();
        System.out.println(jsonResponse);
    }

}
