package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpOrLoginActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword, edtUserNameLogin, edtPasswordLogin;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtPassword = findViewById(R.id.editUserPassword);
        edtUserName = findViewById(R.id.editUserName);
        edtUserNameLogin = findViewById(R.id.editUserNameLogin);
        edtPasswordLogin = findViewById(R.id.editPasswordLogIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            Toast.makeText(SignUpOrLoginActivity.this, appUser.get("username") +  " is Signed up successfully",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(SignUpOrLoginActivity.this , WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpOrLoginActivity.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(),
                        edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e == null) {
                            Toast.makeText(SignUpOrLoginActivity.this, user.get("username") +  " is Logged in successfully",
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpOrLoginActivity.this , WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpOrLoginActivity.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
