package com.example.ardent.myschoolbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;


public class HomeScreen extends AppCompatActivity {

    List<StudentData> studentDataList;
    StudentData studentData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        final RecyclerView listHomeScreen = (RecyclerView) findViewById(R.id.listHomeScreen);
        listHomeScreen.setLayoutManager(new LinearLayoutManager(this));

        String[] lang = {"java","javascript","c","c++","python"};

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Details").child(user.getUid()).child("childern");

        studentDataList=new ArrayList<>();

        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
//                UserData userData = dataSnapshot.getValue(UserData.class);
                // [START_EXCLUDE]
//                Log.w("PostDetailActivity",  userData.name);
//                textViewProfileName.setText(userData.name);
//                textViewProfileEmail.setText(userData.email);
//                textViewProfileContact.setText(userData.contact);
                // [END_EXCLUDE]

                //studentDataList.clear();
                Log.d("SNAP", "onDataChange: " + dataSnapshot.toString());

                    //for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                       // if ("class com.google.firebase.database.DataSnapshot" == userSnapshot.getClass().toString()) {
                            Log.d("LOOP", "onDataChange: ");
                            studentData = dataSnapshot.getValue(StudentData.class);
                            Log.w("NILAKSHI", "hi "+ studentData.name);

                            //studentDataList.add(studentData);
                      //  }
                        //Log.d("KUSHTEPPALWAR", "onDataChange: "   + userSnapshot.getClass().toString());

                    //}
                //UserDetails adapter =new UserDetails(ProfileAct.this,studentDataList);
                //listViewuser.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                 //Getting Post failed, log a message
                Log.w("PostDetailActivity", "loadPost:onCancelled", databaseError.toException());
                 //[START_EXCLUDE]
                Toast.makeText(HomeScreen.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        databaseReference.addValueEventListener(profileListener);
        listHomeScreen.setAdapter(new ProgramingAdapter(studentData));

    }
}
