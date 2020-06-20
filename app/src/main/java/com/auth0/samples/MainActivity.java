package com.auth0.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.auth0.android.Auth0;

/**
 * Created by lbalmaceda on 5/10/17.
 */

public class MainActivity extends Activity {

    private Auth0 auth0;
    String str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button loginButton = findViewById(R.id.logout);
        Button btngenerate = findViewById(R.id.generate);
        Button btnform = findViewById(R.id.form);
        Intent intent = getIntent();
         str = intent.getStringExtra("message");
        btnform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent= new Intent(MainActivity.this, FillForm.class);
                startActivity(intent);
            }
        });

        btngenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GeneratePassport.class);
                intent.putExtra("message2", str);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        //Obtain the token from the Intent's extras
        String accessToken = getIntent().getStringExtra(LoginActivity.EXTRA_ACCESS_TOKEN);
        TextView textView = findViewById(R.id.credentials);
        textView.setText(accessToken);
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.EXTRA_CLEAR_CREDENTIALS, true);
        startActivity(intent);
        finish();
    }
}
