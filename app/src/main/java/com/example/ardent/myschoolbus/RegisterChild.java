package com.example.ardent.myschoolbus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterChild extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegChildSave;
    private EditText editTextRegChildName;
    private EditText editTextRegChildId;

    private DatabaseReference databaseReference;

    //private boolean flag = false;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_child);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Details").child(user.getUid());

        editTextRegChildId = (EditText) findViewById(R.id.editTextRegChildId);
        editTextRegChildName = (EditText) findViewById(R.id.editTextRegChildName);
        buttonRegChildSave = (Button) findViewById(R.id.buttonRegChildSave);

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
        }
        if(TextUtils.isEmpty(id)){
            Toast.makeText(this,"Please enter name", Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Registering ... ");
        progressDialog.show();

        String iid = databaseReference.push().getKey();
        StudentData studentData = new StudentData(name,id);
        databaseReference.child(iid).setValue(studentData);

        Toast.makeText(RegisterChild.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(getApplicationContext(),Profile.class));

        /*firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registered Successfully", Toast.LENGTH_SHORT).show();

                    String password = editTextPassword.getText().toString().trim();
                    String name = editTextName.getText().toString().trim();
                    String contact = editTextContact.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();

                }
                else{
                    Toast.makeText(Register.this,"Try Again...", Toast.LENGTH_SHORT).show();
                    *finish();
                    startActivity(new Intent(getApplicationContext(),Register.class));*
                }
            }
        });*/
    }

}

