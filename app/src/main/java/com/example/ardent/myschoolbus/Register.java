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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextContact;

    private DatabaseReference databaseReference;

    private boolean flag = false;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Details");

        buttonRegister = (Button) findViewById(R.id.buttonRegister);


        editTextContact = (EditText) findViewById(R.id.editTextContactNumber);
        editTextName = (EditText) findViewById(R.id.editTextParentName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailReg);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordReg);


        buttonRegister.setOnClickListener(this);


    }
    private void registerUser(){
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            //email is empty
            Toast.makeText(this,"Please enter name", Toast.LENGTH_SHORT).show();
            //stopping d func exc fur
            return;
        }
        if(TextUtils.isEmpty(contact)){
            //email is empty
            Toast.makeText(this,"Please enter contact number", Toast.LENGTH_SHORT).show();
            //stopping d func exc fur
            return;
        }

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            //stopping d func exc fur
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }




        progressDialog.setMessage("Registering ... ");
        progressDialog.show();

        String id = databaseReference.push().getKey();
        UserData userData = new UserData(name,contact,email,password);
        databaseReference.child(id).setValue(userData);
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){

                    Toast.makeText(Register.this,"Registered Successfully", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
                else{
                    Toast.makeText(Register.this,"Try Again...", Toast.LENGTH_SHORT).show();
                    /*finish();
                    startActivity(new Intent(getApplicationContext(),Register.class));*/
                }
            }
        });

        /*if(flag == true){
            UserData userData = new UserData(name,contact,email,password);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userData);
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }*/

    }


    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            //yaha login kar ke andar jayega
            registerUser();


        }
    }
}
