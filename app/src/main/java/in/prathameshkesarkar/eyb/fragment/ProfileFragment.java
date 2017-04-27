package in.prathameshkesarkar.eyb.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import in.prathameshkesarkar.eyb.R;
import in.prathameshkesarkar.eyb.Utils;
import in.prathameshkesarkar.eyb.model.Profile;
import in.prathameshkesarkar.eyb.network.ProfileService;
import in.prathameshkesarkar.eyb.service.ServiceGenerator;
import io.realm.Realm;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prathameshkesarkar on 08/04/17.
 */

public class ProfileFragment extends BaseFragment {
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_profile;
    }

    @Override
    public void inCreateView(View rootView, ViewGroup container, Bundle savedInstanceState) {

        Realm.init(getActivity());


        OkHttpClient.Builder okHttpClientBuilder = ServiceGenerator.okHttpClient().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("x-auth", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1OGZlNmFkYzc0YjdkYWM4ODRmMWJlY2IiLCJhY2Nlc3MiOiJhdXRoIiwiaWF0IjoxNDkzMjc3NTI0fQ.j41KE9I2skewGXV9f-9blOK5HIa12EAFKnSyyZRmH0E");

                return chain.proceed(builder.build());
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                                    .baseUrl(Utils.BASE_URL)
                                    .client(okHttpClientBuilder.build())
                                    .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();


        ProfileService profileService = retrofit.create(ProfileService.class);

        Call<Profile> profileCall = profileService.getUserProfile("58fe6adc74b7dac884f1becb");

        profileCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {


                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                Profile profile =realm.copyToRealm(response.body());
                realm.commitTransaction();
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });

    }
}
