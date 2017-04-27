package in.prathameshkesarkar.eyb.service;

import okhttp3.OkHttpClient;
import okhttp3.internal.connection.ConnectInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "http://192.168.0.103:3000/";

    private static OkHttpClient.Builder httpclient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpclient.build())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();


    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
    public static Retrofit retrofit(){
        return retrofit;
    }
}
