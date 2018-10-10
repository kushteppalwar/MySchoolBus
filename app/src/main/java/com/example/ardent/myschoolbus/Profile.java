package com.example.ardent.myschoolbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewProfileName;
    private TextView textViewProfileEmail;
    private TextView textViewProfileContact;
    private Button buttonProfileAddStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewProfileName = (TextView) findViewById(R.id.textviewProfileName);
        textViewProfileEmail = (TextView) findViewById(R.id.textviewProfileEmail);
        textViewProfileContact = (TextView) findViewById(R.id.textviewProfileContact);
        buttonProfileAddStudent = (Button) findViewById(R.id.buttonProfileHome);
        //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = null;
        if (user != null) {
            databaseReference = firebaseDatabase.getReference().child("Details").child(user.getUid()).child("Parent");
        }


        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UserData userData = dataSnapshot.getValue(UserData.class);
                // [START_EXCLUDE]
                Log.w("PostDetailActivity", userData != null ? userData.name : null);
                textViewProfileName.setText(userData != null ? userData.name : null);
                textViewProfileEmail.setText(userData != null ? userData.email : null);
                textViewProfileContact.setText(userData != null ? userData.contact : null);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w("PostDetailActivity", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(Profile.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };


        if (databaseReference != null) {
            databaseReference.addValueEventListener(profileListener);
        }

        buttonProfileAddStudent.setOnClickListener( this);

    }

    @Override
    public void onClick(View v) {
        if(v==buttonProfileAddStudent){
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }
    }
}
