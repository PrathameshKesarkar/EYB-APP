package in.prathameshkesarkar.eyb.network;

import in.prathameshkesarkar.eyb.model.Login;
import in.prathameshkesarkar.eyb.model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public interface LoginService {

    @POST("/users/login")
    Call<RegisterResponse> performLogin(@Body Login request);
}
