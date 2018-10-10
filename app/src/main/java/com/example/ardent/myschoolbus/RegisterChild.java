package com.example.ardent.myschoolbus;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterChild extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegChildSave;
    private EditText editTextRegChildName;
    //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    //databaseReference = FirebaseDatabase.getInstance().getReference("Details").child(user.getUid()).child("children");

    private EditText editTextRegChildId;

    //private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_child);

        buttonRegChildSave = (Button) findViewById(R.id.buttonRegChildSave);
        editTextRegChildName = (EditText) findViewById(R.id.editTextRegChildName);
        editTextRegChildId = (EditText) findViewById(R.id.editTextRegChildId);

        ProgressDialog progressDialog = new ProgressDialog(this);

        buttonRegChildSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonRegChildSave){
            registerchilduser();
        }
    }

    private void registerchilduser() {
        String name = editTextRegChildName.getText().toString().trim();
        String id = editTextRegChildId.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(id)){
            Toast.makeText(this,"Please enter ID", Toast.LENGTH_SHORT).show();
            return;
        }

//        progressDialog.setMessage("Registering ... ");
//        progressDialog.show();

//        DatabaseReference newStd = databaseReference.push();
//        StudentData studentData = new StudentData(name,id);
//        newStd.setValue(studentData);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        StudentData studentData = new StudentData(name,id);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            databaseReference.child("Details").child(user.getUid()).child("Children").setValue(studentData);
        }

        Toast.makeText(RegisterChild.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(getApplicationContext(),Home.class));
    }

}

