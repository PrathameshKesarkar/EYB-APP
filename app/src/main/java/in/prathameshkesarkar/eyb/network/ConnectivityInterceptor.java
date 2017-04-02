package in.prathameshkesarkar.eyb.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import in.prathameshkesarkar.eyb.model.exception.NetworkException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class ConnectivityInterceptor implements Interceptor {

    Context context;

    public ConnectivityInterceptor(Context context){
        this.context=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!isOnline(context)){
            throw new NetworkException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    private boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
