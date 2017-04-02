package in.prathameshkesarkar.eyb.network;

import java.io.IOException;
import java.lang.annotation.Annotation;

import in.prathameshkesarkar.eyb.model.error.RetrofitError;
import in.prathameshkesarkar.eyb.service.ServiceGenerator;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class ErrorUtils {
    public static RetrofitError parseError(Response<?> response) {

        Converter<ResponseBody, RetrofitError> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(RetrofitError.class, new Annotation[0]);
        RetrofitError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            return new RetrofitError();
        }
        return error;
    }
}
