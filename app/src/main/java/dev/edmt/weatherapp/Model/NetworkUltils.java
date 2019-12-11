package dev.edmt.weatherapp.Model;

import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import retrofit2.http.Url;

public class NetworkUltils {
    private final static String Weather_Base_Url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/{186108}";
    private static final String TAG="NetworkUtils";
    private final static String ApiKey = "LJijvJmXbgdkz746lTHzQ4POEyQteIGS";
    private final static  String PARAM_API_KEY= "api_key";
    public static URL BuildUrlForWeather() {
        Uri builtUri = Uri.parse(Weather_Base_Url).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,ApiKey)
                .build();
        URL url = null;
        try {
            url=new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.i(TAG,"built url for weather: url:"+ url);
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner=new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput=scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }

        }
        finally {
            urlConnection.disconnect();
        }
    }
}