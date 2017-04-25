package id.sch.smktelkom_mlg.letsadventure;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rehan on 26/04/2017.
 */

public interface SignUp {
    @FormUrlEncoded
    @POST("user/register")
    Call<Response> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("is_parent") Boolean is_parent
    );

    @FormUrlEncoded
    @POST("user/push")
    Call<Response> registerPush(
            @Field("auth") String auth,
            @Field("pushID") String pushID
    );

    @FormUrlEncoded
    @POST("user/child")
    Call<Response> registerChild(
            @Field("auth") String auth,
            @Field("invite") String invite
    );
}
