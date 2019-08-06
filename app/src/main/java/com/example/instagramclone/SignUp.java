package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnGetAll;
    private EditText name, punchSpeed, punchPower, kickSpeed, kickPower;
    private String allKickBoxers;
    private TextView txtGetData;

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
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAll = findViewById(R.id.btnGetAll);
        btnSave.setOnClickListener(SignUp.this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("Zd7DIaTl87", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText(object.get("name") + "");
                        }
                    }
                });

            }
        });
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allKickBoxers = "";
                final ParseQuery<ParseObject> allQuery = ParseQuery.getQuery("KickBoxer");
                allQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject kickBoxers : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxers.get("name") + "\n";
                                }
                                Toast.makeText(SignUp.this, allKickBoxers,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUp.this, "Failure",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
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
