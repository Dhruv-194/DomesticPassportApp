package com.auth0.samples;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillForm extends AppCompatActivity {

    EditText regCurrentCity, regGoingto, regName, regPermAdd, regDob, regGender;
    Button btnFill;
    String keyo;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        regCurrentCity= findViewById(R.id.editcurrentcity);
        regGoingto = findViewById(R.id.goingcurrentcity);
        regName = findViewById(R.id.editname);
        regPermAdd = findViewById(R.id.editaddress);
        regDob = findViewById(R.id.editdob);
        regGender = findViewById(R.id.editgender);
        btnFill = findViewById(R.id.buttonfill);

        btnFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                 keyo = rootNode.getReference("Users").push().getKey();

                String scurrentcity = regCurrentCity.getText().toString();
                String sgoingtocity = regGoingto.getText().toString();
                String sname = regName.getText().toString();
                String spermaddress = regPermAdd.getText().toString();
                String sdob = regDob.getText().toString();
                String sgender = regGender.getText().toString();

                HelperClass helperclass = new HelperClass(scurrentcity, sgoingtocity, sname, spermaddress, sdob, sgender);

                reference.child(keyo).setValue(helperclass);

                Toast.makeText(FillForm.this, "Successfully Registered", Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        btnFill.setEnabled(false);
                        regCurrentCity.setText("");
                        regGoingto.setText("");
                        regName.setText("");
                        regDob.setText("");
                        regGender.setText("");
                        regPermAdd.setText("");
                        Intent intent = new Intent (FillForm.this, GeneratePassport.class);
                        intent.putExtra("message", keyo);
                        startActivity(intent);
                        Toast.makeText(FillForm.this, "Go to generate passport", Toast.LENGTH_LONG).show();

                    }
                },3000);

            }
        });


    }
}