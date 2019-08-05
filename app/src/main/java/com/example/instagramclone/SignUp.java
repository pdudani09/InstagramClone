package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText name , punchSpeed, punchPower , kickSpeed, kickPower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.btnSave);
        name = findViewById(R.id.kbName);
        punchSpeed = findViewById(R.id.kbPunchSpeed);
        punchPower = findViewById(R.id.kbPunchPower);
        kickSpeed = findViewById(R.id.kbKickSpeed);
        kickPower = findViewById(R.id.kbKickPower);

        btnSave.setOnClickListener(SignUp.this);
    }

    @Override
    public void onClick(View view) {
        {

            try {
                final ParseObject kickBoxer = new ParseObject("KickBoxer");
                kickBoxer.put("name", name.getText().toString());
                kickBoxer.put("punch_speed", Integer.parseInt(punchSpeed.getText().toString()));
                kickBoxer.put("punch_power", Integer.parseInt(punchPower.getText().toString()));
                kickBoxer.put("kick_speed", Integer.parseInt(kickSpeed.getText().toString()));
                kickBoxer.put("kick_power", Integer.parseInt(kickPower.getText().toString()));
                kickBoxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(SignUp.this, kickBoxer.get("name") + " Object is saved successfully",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignUp.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } catch (Exception e) {
                Toast.makeText(SignUp.this, e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

        }
    }
}
