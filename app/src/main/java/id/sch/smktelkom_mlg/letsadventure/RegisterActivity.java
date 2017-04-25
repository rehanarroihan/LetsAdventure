package id.sch.smktelkom_mlg.letsadventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private String TAG = "RegisterActivity";

    private EditText etUsername, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnStart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        etUsername = (EditText) findViewById(R.id.editTextRegUser);
        etEmail = (EditText) findViewById(R.id.editTextRegEmail);
        etPassword = (EditText) findViewById(R.id.editTextRegPasswd);
    }
}
