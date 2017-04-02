package in.prathameshkesarkar.eyb.network;

import in.prathameshkesarkar.eyb.model.Register;
import in.prathameshkesarkar.eyb.model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by prathameshkesarkar on 01/04/17.
 */

public interface RegisterService {

    @POST("/users/register")
    Call<RegisterResponse> performRegister(@Body Register request);
}
