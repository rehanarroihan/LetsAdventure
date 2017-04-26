package id.sch.smktelkom_mlg.letsadventure;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import id.sch.smktelkom_mlg.letsadventure.interfaces.RegisterInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private String username, password;
    private Button btLog;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.editTextLogUsername);
        etPassword = (EditText) findViewById(R.id.editTextLogPass);
        btLog = (Button) findViewById(R.id.buttonLogin);
        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    private void doLogin() {
        if (isValid()) {
            //DOLOGIN
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Logging in, please wait...");
            progress.show();

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(GlobalSettings.ServerURL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

            Retrofit retrofit = builder.client(httpClient.build()).build();
            RegisterInterface r = retrofit.create(RegisterInterface.class);
            Call<Response> call = r.loginToServer(username, password);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Response r = response.body();

                    if (r.getSuccess() == true) {
                        progress.dismiss();
                        Prefs.putString("LoggedUsername", username);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "Login Failed, Try Again Later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Failed, Try Again Later", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isValid() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Email dibutuhkan");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password harus diisi");
            return false;
        }
        return true;
    }
}
