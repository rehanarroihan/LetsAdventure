package id.sch.smktelkom_mlg.letsadventure.fragment;


import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import id.sch.smktelkom_mlg.letsadventure.GlobalSettings;
import id.sch.smktelkom_mlg.letsadventure.R;
import id.sch.smktelkom_mlg.letsadventure.interfaces.ProfileInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private TextView tvUsername, tvEmail;
    private Button btLogOut;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvUsername = (TextView) getView().findViewById(R.id.textViewUsername);
        tvEmail = (TextView) getView().findViewById(R.id.textViewEmail);
        btLogOut = (Button) getView().findViewById(R.id.buttonLogout);
        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Prefs.Builder()
                        .setContext(getActivity().getApplicationContext())
                        .setMode(ContextWrapper.MODE_PRIVATE)
                        .setPrefsName(getActivity().getPackageName())
                        .setUseDefaultSharedPreference(true)
                        .build();

                Prefs.putString("LoggedUsername", null);
                getActivity().finish();
            }
        });

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(GlobalSettings.ServerURL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );

        Retrofit retrofit = builder.client(httpClient.build()).build();
        ProfileInterface p = retrofit.create(ProfileInterface.class);
        Call<ProfileResponse> call = p.getInfo(Prefs.getString("LoggedUsername", null));
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, retrofit2.Response<ProfileResponse> response) {
                ProfileResponse p = response.body();
                tvUsername.setText(p.getFullName());
                tvEmail.setText(p.getEmail());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }
}
