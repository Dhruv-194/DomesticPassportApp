package com.auth0.samples;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratePassport extends AppCompatActivity {

    Button btnGenerate;
    TextView tcurrentcity, tgoingcity, tname, tdob, tpremadd, tgender, tissuedate, texpdate;
    DatabaseReference reff;
    String keyo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_passport);
        btnGenerate= findViewById(R.id.nowgenerate);
        tcurrentcity = findViewById(R.id.currentcity);
        tgoingcity = findViewById(R.id.goingcity);
        tname = findViewById(R.id.name);
        tdob= findViewById(R.id.dob);
        tpremadd = findViewById(R.id.address);
        tgender = findViewById(R.id.sex);
        tissuedate = findViewById(R.id.issuedate);
        texpdate = findViewById(R.id.expdate);

       /* Intent intent = getIntent();
        keyo = intent.getStringExtra("message");*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G");
        final String currentDate = sdf.format(new Date());

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child("MAIuaZeq5cpgdT7e-r1");
                //Log.i("see1",keyo);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String currentcity = dataSnapshot.child("hcurrentcity").getValue().toString();
                        String goingcity = dataSnapshot.child("hgoingtocity").getValue().toString();
                        String name = dataSnapshot.child("hname").getValue().toString();
                        String premadd = dataSnapshot.child("hpermaddress").getValue().toString();
                        String dob = dataSnapshot.child("hdob").getValue().toString();
                        String gender = dataSnapshot.child("hgender").getValue().toString();

                        tcurrentcity.setText(currentcity);
                        tgoingcity.setText(goingcity);
                        tname.setText(name);
                        tpremadd.setText(premadd);
                        tdob.setText(dob);
                        tgender.setText(gender);
                        tissuedate.setText(currentDate);
                        texpdate.setText("2023");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(GeneratePassport.this, "Sorry an error occurred", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}