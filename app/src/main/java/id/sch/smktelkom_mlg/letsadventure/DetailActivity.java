package id.sch.smktelkom_mlg.letsadventure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.sch.smktelkom_mlg.letsadventure.interfaces.PariwisataInterface;
import id.sch.smktelkom_mlg.letsadventure.model.Pariwisata;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getIntent().getStringExtra("title"));

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(GlobalSettings.ServerURL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );

        String id = getIntent().getExtras().getString("id");
        Retrofit retrofit = builder.client(httpClient.build()).build();
        PariwisataInterface p = retrofit.create(PariwisataInterface.class);
        Call<Pariwisata> call = p.getInfo(id);
        call.enqueue(new Callback<Pariwisata>() {
            @Override
            public void onResponse(Call<Pariwisata> call, retrofit2.Response<Pariwisata> response) {
                final Pariwisata ps = response.body();

                setTitle(ps.getJudul_wisata());
                TextView tvDetail = (TextView) findViewById(R.id.place_detail);
                tvDetail.setText(ps.getDetail_wisata());

                TextView tvLocation = (TextView) findViewById(R.id.place_location);
                tvLocation.setText(ps.getLokasi());

                ImageView ivLoad = (ImageView) findViewById(R.id.imageFoto);

                String link = ps.getGambar();
                if (ps.getGambar().contains("imgur.com")) {
                    String newLink = link.replace("imgur.com", "i.imgur.com");
                    link = newLink + ".jpg";
                }
                Picasso.with(getApplicationContext()).load(link).into(ivLoad);

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri map = Uri.parse("geo:" + ps.getKoordinat().trim());
                        Intent maps = new Intent(Intent.ACTION_VIEW, map);
                        maps.setPackage("com.google.android.apps.maps");
                        if (maps.resolveActivity(getPackageManager()) != null) {
                            startActivity(maps);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<Pariwisata> call, Throwable t) {

            }
        });

    }
}
