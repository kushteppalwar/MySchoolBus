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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth ;
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        finish();
//        startActivity(new Intent(getApplicationContext(),temp.class));
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()!=null){
//            // profil act start hogi
//
//            startActivity(new Intent(getApplicationContext(),Home.class));
//            finish();
//        }

        buttonLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void loginUser(){
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

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

//        progressDialog.setMessage("Logging In ... ");
//        progressDialog.show();



        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Logged In", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        //yaha login kar ke andar jayega
        if(v == buttonLogin) loginUser();

        if(v == textViewSignup){
            //signupUser();
            //registerUser();
            finish();
            startActivity(new Intent(getApplicationContext(),Register.class));
        }
    }
}
