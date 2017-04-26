package id.sch.smktelkom_mlg.letsadventure;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pixplicity.easyprefs.library.Prefs;

import id.sch.smktelkom_mlg.letsadventure.interfaces.RegisterInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {
    private String TAG = "RegisterActivity";

    private EditText etUsername, etEmail, etPassword, etFullName;
    private Button btRegister;

    private String username, password, email, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnStart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        etUsername = (EditText) findViewById(R.id.editTextRegUser);
        etEmail = (EditText) findViewById(R.id.editTextRegEmail);
        etPassword = (EditText) findViewById(R.id.editTextRegPasswd);
        etFullName = (EditText) findViewById(R.id.editTextRegName);
        btRegister = (Button) findViewById(R.id.buttonRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    private void doRegister() {
        if (isValid()) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(GlobalSettings.ServerURL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

            Retrofit retrofit = builder.client(httpClient.build()).build();
            RegisterInterface r = retrofit.create(RegisterInterface.class);
            Call<Response> call = r.registerToServer(username, password, email, fullname);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Response r = response.body();

                    if (r.getSuccess() == true) {
                        Prefs.putString("LoggedUsername", username);
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
        }
    }

    private boolean isValid() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        fullname = etFullName.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Username dibutuhkan");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password harus diisi");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            etPassword.setError("Email harus diisi");
            return false;
        }
        if (TextUtils.isEmpty(fullname)) {
            etPassword.setError("Fullname harus diisi");
            return false;
        }
        return true;
    }
}
