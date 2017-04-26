package id.sch.smktelkom_mlg.letsadventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private String email, password;
    private Button btLog;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.editTextLogEmail);
        etPassword = (EditText) findViewById(R.id.editTextLogPass);
        btLog = (Button) findViewById(R.id.buttonLogin);
        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        if (isValid()) {
            //DOLOGIN
        }
    }

    private boolean isValid() {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email dibutuhkan");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password harus diisi");
            return false;
        }
        return true;
    }
}
