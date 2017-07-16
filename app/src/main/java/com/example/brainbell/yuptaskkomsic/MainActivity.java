package com.example.brainbell.yuptaskkomsic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String savedUsername;
    private String savedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout facebookLogInLayout = (LinearLayout) findViewById(R.id.facebook_log_in_layout);
        setOnClick(facebookLogInLayout);

        TextView forgotPasswordTxt = (TextView) findViewById(R.id.forgot_password_txt);
        setOnClick(forgotPasswordTxt);

        TextView signUpTxt = (TextView) findViewById(R.id.sign_up_txt);
        setOnClick(signUpTxt);

        TextView logInTxt = (TextView) findViewById(R.id.log_in_txt);
        validate(logInTxt);

    }

    private void validate(View view) {
        final EditText usernameEdt = (EditText) findViewById(R.id.username_edt);
        final EditText passwordEdt = (EditText) findViewById(R.id.password_edt);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEdt.getText().toString().length() > 3 |
                        passwordEdt.getText().toString().length() > 6) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    intent.putExtra("username", usernameEdt.getText().toString());
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username Or Password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setOnClick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Feature Not Working Yet",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
