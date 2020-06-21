package com.auth0.samples;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GeneratePassport extends AppCompatActivity {

    Button btnGenerate;
    TextView tcurrentcity, tgoingcity, tname, tdob, tpremadd, tgender, tissuedate, texpdate, tunique;
   FirebaseDatabase database;
    DatabaseReference reff;
    String keyo;
    ImageView qrImage;
    Bitmap bitmap;

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
        tunique = findViewById(R.id.uniqne);
        qrImage = findViewById(R.id.qrPlaceHolder);

      Intent intent = getIntent();
        keyo = intent.getStringExtra("message");
        Log.d("see1",keyo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G");
        final String currentDate = sdf.format(new Date());

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                QRGEncoder qrgEncoder = new QRGEncoder(keyo, null, QRGContents.Type.TEXT, 100);
                try {
                    // Getting QR-Code as Bitmap
                    bitmap = qrgEncoder.getBitmap();
                    // Setting Bitmap to ImageView
                    qrImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }



                reff = FirebaseDatabase.getInstance().getReference("Users").child(keyo);
                //Log.i("see1",keyo);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Object currentcity = dataSnapshot.child("hcurrentcity").getValue();
                            if (currentcity==null){Toast.makeText(GeneratePassport.this, "It is null", Toast.LENGTH_SHORT).show();}
                          String ccnew = currentcity.toString();

                            Object goingcity = dataSnapshot.child("hgoingtocity").getValue();
                        if (goingcity==null){Toast.makeText(GeneratePassport.this, "It is null", Toast.LENGTH_SHORT).show();}
                        String gcnew = goingcity.toString();

                        Object name = dataSnapshot.child("hname").getValue();
                        if (name==null){Toast.makeText(GeneratePassport.this, "It is null", Toast.LENGTH_SHORT).show();}
                        String namenew = name.toString();

                        Object permadd = dataSnapshot.child("hpermaddress").getValue();
                        if (permadd==null){Toast.makeText(GeneratePassport.this, "It is null", Toast.LENGTH_SHORT).show();}
                        String permaddnew = permadd.toString();

                        Object dob = dataSnapshot.child("hdob").getValue();
                        if (dob==null){Toast.makeText(GeneratePassport.this, "It is null", Toast.LENGTH_SHORT).show();}
                        String dobnew = dob.toString();

                        Object gender = dataSnapshot.child("hgender").getValue();
                        if (gender==null){Toast.makeText(GeneratePassport.this, "It is null", Toast.LENGTH_SHORT).show();}
                        String gendernew = gender.toString();

                            /*String goingcity = dataSnapshot.child("hgoingtocity").getValue().toString();
                            String name = dataSnapshot.child("hname").getValue().toString();
                            String premadd = dataSnapshot.child("hpermaddress").getValue().toString();
                            String dob = dataSnapshot.child("hdob").getValue().toString();
                            String gender = dataSnapshot.child("hgender").getValue().toString();*/

                           tcurrentcity.setText(ccnew);
                            tgoingcity.setText(gcnew);
                            tname.setText(namenew);
                            tpremadd.setText(permaddnew);
                            tdob.setText(dobnew);
                            tgender.setText(gendernew);
                            tissuedate.setText(currentDate);
                            texpdate.setText("2023");
                            tunique.setText(keyo);

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