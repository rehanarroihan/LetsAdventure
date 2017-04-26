package id.sch.smktelkom_mlg.letsadventure.interfaces;

import id.sch.smktelkom_mlg.letsadventure.Response;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rehan on 26/04/2017.
 */

public interface RegisterInterface {
    @FormUrlEncoded
    @POST("/service/register")
    Call<Response> registerToServer(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("fullname") String FullName
    );

    @FormUrlEncoded
    @POST("/service/login")
    Call<Response> loginToServer(
            @Field("username") String username,
            @Field("password") String password
    );
}
