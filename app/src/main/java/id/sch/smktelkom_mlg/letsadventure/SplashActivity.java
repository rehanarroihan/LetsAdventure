package id.sch.smktelkom_mlg.letsadventure;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pixplicity.easyprefs.library.Prefs;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        if (!isConnect(this)) {
            new AlertDialog.Builder(SplashActivity.this)
                    .setTitle("Failed")
                    .setMessage("No internet connection")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }

        Boolean FirstTime = Prefs.getBoolean("isFirstTime", true);
        if (FirstTime == true) {
            Prefs.putBoolean("isFirstTime", false);
            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            finish();
        } else {
            checkLogin();
        }
    }

    private void checkLogin() {
        String u = Prefs.getString("LoggedUsername", null);
        if (u == null) {
            startActivity(new Intent(SplashActivity.this, LandingActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }

        finish();
    }

    public boolean isConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
