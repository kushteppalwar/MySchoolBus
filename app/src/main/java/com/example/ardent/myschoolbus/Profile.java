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


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewProfileContact = (TextView) findViewById(R.id.textviewProfileContact);
        textViewProfileEmail = (TextView) findViewById(R.id.textviewProfileEmail);
        textViewProfileName = (TextView) findViewById(R.id.textviewProfileName);
        buttonProfileAddStudent = (Button) findViewById(R.id.buttonProfileAddStudent);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Details").child(user.getUid());
        /*
        //get firebase user
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    //get reference
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Details");

                    UserData userData = new UserData(name,contact,email,password);
                    //build child
                    ref.child(user.getUid()).setValue(userData);
        */

        //UserData userData = new UserData(name,contact,email,password);

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserData userData = dataSnapshot.getValue(UserData.class);
                textViewProfileName.setText(userData.getName());
                //textViewProfileName.setText("nilu");
                //Log.d("MESSAGE",userData.getName());
                    //Log.d("MESSAGE","fdsgtsdf");
                textViewProfileContact.setText(userData.getContact());
                textViewProfileEmail.setText(userData.getEmail());
                Toast.makeText(Profile.this,"aa gaya data...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });*/


        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UserData userData = dataSnapshot.getValue(UserData.class);
                // [START_EXCLUDE]
                Log.w("PostDetailActivity",  userData.name);
                textViewProfileName.setText(userData.name);
                textViewProfileEmail.setText(userData.email);
                textViewProfileContact.setText(userData.contact);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w("PostDetailActivity", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(Profile.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        databaseReference.addValueEventListener(profileListener);

    buttonProfileAddStudent.setOnClickListener( this);

    }

    @Override
    public void onClick(View v) {
        if(v==buttonProfileAddStudent){
            finish();
            startActivity(new Intent(getApplicationContext(),RegisterChild.class));
        }
    }
}
