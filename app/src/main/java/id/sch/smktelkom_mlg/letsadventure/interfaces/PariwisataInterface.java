package id.sch.smktelkom_mlg.letsadventure.interfaces;

import java.util.List;

import id.sch.smktelkom_mlg.letsadventure.model.Pariwisata;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rehan on 26/04/2017.
 */

public interface PariwisataInterface {
    @GET("/place/all")
    Call<List<Pariwisata>> getPariwisata(

    );

    @GET("/place/detail")
    Call<Pariwisata> getInfo(
            @Query("id") String id
    );
}
