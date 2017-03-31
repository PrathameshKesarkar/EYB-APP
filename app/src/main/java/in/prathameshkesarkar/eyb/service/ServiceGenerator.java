package in.prathameshkesarkar.eyb.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "http://192.168.1.101:3000/";

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpclient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
