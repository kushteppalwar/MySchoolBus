package com.example.ardent.myschoolbus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private Button buttonLogout;
    private Button buttonProfile;
    private Button buttonStudprofile;
    private Button buttonTrack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()==null){
//            finish();
//            startActivity(new Intent(this,MainActivity.class));
//        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//
//        TextView textViewUserEmail = (TextView) findViewById(R.id.textviewHomeEmail);
//
//        textViewUserEmail.setText("Welcome " + user.getEmail() );
//
        buttonLogout = (Button) findViewById(R.id.buttonHomeLogout);
        buttonStudprofile = (Button) findViewById(R.id.buttonHomeStudentProfile);
        buttonProfile = (Button) findViewById(R.id.buttonHomeProfile);
        buttonTrack = (Button)findViewById(R.id.buttonHomeTracking);
        buttonLogout.setOnClickListener(this);
        buttonStudprofile.setOnClickListener(this);
        buttonProfile.setOnClickListener(this);
        buttonTrack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        if(v==buttonStudprofile){
            finish();
            startActivity(new Intent(this,StudentProfile.class));
        }
        if(v==buttonProfile){
            finish();
            startActivity(new Intent(this,Profile.class));
        }
        if(v==buttonTrack){
            finish();
            startActivity(new Intent(this,Tracker.class));
        }
    }
}
