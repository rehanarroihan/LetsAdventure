package id.sch.smktelkom_mlg.letsadventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.letsadventure.adapter.PariwisataAdapter;
import id.sch.smktelkom_mlg.letsadventure.interfaces.ClickListener;
import id.sch.smktelkom_mlg.letsadventure.interfaces.PariwisataInterface;
import id.sch.smktelkom_mlg.letsadventure.interfaces.RecyclerTouchListener;
import id.sch.smktelkom_mlg.letsadventure.model.Pariwisata;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    private List<Pariwisata> pariwisataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PariwisataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        prepareData();
    }

    private void prepareData() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(GlobalSettings.ServerURL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );

        Retrofit retrofit = builder.client(httpClient.build()).build();
        PariwisataInterface p = retrofit.create(PariwisataInterface.class);
        Call<List<Pariwisata>> call = p.getPariwisata();
        call.enqueue(new Callback<List<Pariwisata>>() {
            @Override
            public void onResponse(Call<List<Pariwisata>> call, Response<List<Pariwisata>> response) {
                final List<Pariwisata> pr = response.body();
                mAdapter = new PariwisataAdapter(pr);
                recyclerView.setAdapter(mAdapter);
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Pariwisata p = pr.get(position);
                        Toast.makeText(getApplicationContext(), p.getJudul_wisata(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ListActivity.this, DetailActivity.class);
                        i.putExtra("id", p.get_id());
                        i.putExtra("title", p.getJudul_wisata());
                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Pariwisata>> call, Throwable t) {

            }
        });

    }

    private void addData(Pariwisata p) {
        pariwisataList.add(p);
        mAdapter.notifyDataSetChanged();
        recyclerView.notify();
    }
}
