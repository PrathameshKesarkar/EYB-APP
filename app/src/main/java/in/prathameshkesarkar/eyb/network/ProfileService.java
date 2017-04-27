package in.prathameshkesarkar.eyb.network;

import in.prathameshkesarkar.eyb.model.Profile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by prathameshkesarkar on 27/04/17.
 */

public interface ProfileService {

    @GET("/users/profile")
    Call<Profile> getUserProfile(@Query("id")String creatorId);


}
