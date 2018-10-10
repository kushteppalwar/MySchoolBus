package com.example.ardent.myschoolbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfile extends AppCompatActivity  {

    private TextView textViewStudentProfileName1;
    private TextView textViewStudentProfileId1;

    private Button buttonProfileAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

//        ValueEventListener studentprofileListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                StudentData studentData = dataSnapshot.getValue(StudentData.class);
//                textViewStudentProfileName.setText(studentData != null ? studentData.name : null);
//                textViewStudentProfileId.setText(studentData != null ? studentData.id : null);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                //Log.w("PostDetailActivity", "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
//                Toast.makeText(StudentProfile.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        };


//        if (databaseReference != null) {
//            databaseReference.addValueEventListener(studentprofileListener);
//        }

        //buttonProfileAdd.setOnClickListener( this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        textViewStudentProfileName1 =  findViewById(R.id.studentprofilename);
        textViewStudentProfileId1 =  findViewById(R.id.studentprofileid);
        buttonProfileAdd = (Button) findViewById(R.id.buttonProfileStuHome);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = null;
        if (user != null) {
            databaseReference = firebaseDatabase.getReference().child("Details").child(user.getUid()).child("Children");
        }


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //   StudentData studentData = dataSnapshot.getValue(StudentData.class);

                String s1=dataSnapshot.child("name").getValue().toString();
                String s2=dataSnapshot.child("id").getValue().toString();
                Toast.makeText(getApplicationContext(),dataSnapshot.child("name").getValue().toString(),Toast.LENGTH_LONG).show();
                textViewStudentProfileName1.setText(String.valueOf(s1));
                textViewStudentProfileId1.setText(String.valueOf(s2));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        buttonProfileAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==buttonProfileAdd) {
                    finish();
                    startActivity(new Intent(StudentProfile.this, Home.class));
                }
            }
        });
    }



}
