package mateomartinelli.user2cadem.it.supermercato.Controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by utente2.academy on 12/1/2017.
 */

public class ProductFirebaseRequest {
    private static final String BASE_URL = "https://supermercato-41757.firebaseio.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(url,null,asyncHttpResponseHandler );
    }

    public static void post(String url, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.post(url,null,asyncHttpResponseHandler );
    }

    private static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }
}
