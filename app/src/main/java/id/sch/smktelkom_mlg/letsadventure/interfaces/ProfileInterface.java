package id.sch.smktelkom_mlg.letsadventure.interfaces;

import id.sch.smktelkom_mlg.letsadventure.fragment.ProfileResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rehan on 26/04/2017.
 */

public interface ProfileInterface {

    @GET("/service/profile")
    Call<ProfileResponse> getInfo(
            @Query("username") String username
    );
}
