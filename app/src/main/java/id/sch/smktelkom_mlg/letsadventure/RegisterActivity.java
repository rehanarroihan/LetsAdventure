package id.sch.smktelkom_mlg.letsadventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }

    private void doRegister() {
        if (isValid()) {

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
