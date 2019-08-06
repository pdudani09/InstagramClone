package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmailLogin, edtPasswordLogin;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        edtPasswordLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        btnSignUpLoginActivity.setOnClickListener(this);
        btnLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLoginActivity:

                if (edtPasswordLogin.getText().toString().equals("") ||
                        edtEmailLogin.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, " Email ,  and Password is required",
                            Toast.LENGTH_LONG).show();
                } else {
                    ParseUser.logInInBackground(edtEmailLogin.getText().toString(),
                            edtPasswordLogin.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        Toast.makeText(LoginActivity.this,
                                                user.get("username") + " is Logged in successfully",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

                break;
            case R.id.btnSignUpLoginActivity:
                break;
        }

    }

    public void loginLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
