package in.prathameshkesarkar.eyb.network;

import in.prathameshkesarkar.eyb.model.LoginRequest;
import in.prathameshkesarkar.eyb.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public interface LoginService {

    @POST("/users/login")
    Call<LoginResponse> performLogin(@Body LoginRequest request);
}
