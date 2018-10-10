package com.example.ardent.myschoolbus;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.ardent.myschoolbus.Tracker.PERMISSIONS;
import static com.example.ardent.myschoolbus.Tracker.PERMISSION_ALL;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextContact;



    private static final int REQUEST_LOCATION=1;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth ;

    private DatabaseReference databaseReference;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailReg);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordReg);
        editTextName = (EditText) findViewById(R.id.editTextParentName);
        editTextContact = (EditText) findViewById(R.id.editTextContactNumber);





        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();


        buttonRegister.setOnClickListener(this);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

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


        Task<AuthResult> authResultTask = firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    //String id = databaseReference.push().getKey();
                    String password = editTextPassword.getText().toString().trim();
                    String name = editTextName.getText().toString().trim();
                    String contact = editTextContact.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();






                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    UserData userData = new UserData(name, contact, email, password);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        databaseReference.child("Details").child(user.getUid()).child("Parent").setValue(userData);
                    }


                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        buildAlertMessageNoGps();
                    }
                    else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        getLocation();
                    }

                    finish();
                    startActivity(new Intent(getApplicationContext(),RegisterChild.class));

                } else {
                    Toast.makeText(Register.this, "Try Again...", Toast.LENGTH_SHORT).show();
                    /*finish();
                    startActivity(new Intent(getApplicationContext(),Register.class));*/
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            //yaha login kar ke andar jayega
            registerUser();

            //getLocation();
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void getLocation(){
//        String latitude;
//        String longitude;
        if(ActivityCompat.checkSelfPermission(Register.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Register.this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }

        else{
            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location location1=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location location2=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if(location!=null){
                double latti=location.getLatitude();
                double longi=location.getLongitude();
//                latitude =String.valueOf(latti);
//                longitude=String.valueOf(longi);
                databaseReference = FirebaseDatabase.getInstance().getReference();
                Locaten locaten = new Locaten(latti,longi);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    databaseReference.child("Details").child(user.getUid()).child("Location").setValue(locaten);
                }
//                textView.setText("Your current location is"+"\n"+"Lattitude="+latitude+"\n"+"Longitude="+longitude+"\n"+"Locality="+getLocationName(latti,longi));
                Toast.makeText(this,"Location mil gaya",Toast.LENGTH_SHORT).show();

            }
            else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
//                latitude = String.valueOf(latti);
//                longitude = String.valueOf(longi);
                databaseReference = FirebaseDatabase.getInstance().getReference();
                Locaten locaten = new Locaten(latti,longi);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    databaseReference.child("Details").child(user.getUid()).child("Location").setValue(locaten);
                }
//                textView.setText("Your current location is"+ "\n" + "Lattitude = " + latitude
//                        + "\n" + "Longitude = " + longitude);
                Toast.makeText(this,"Location mil gaya",Toast.LENGTH_SHORT).show();


            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
//                latitude = String.valueOf(latti);
//                longitude = String.valueOf(longi);
                databaseReference = FirebaseDatabase.getInstance().getReference();
                Locaten locaten = new Locaten(latti,longi);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    databaseReference.child("Details").child(user.getUid()).child("Location").setValue(locaten);
                }
                Toast.makeText(this,"Location mil gaya",Toast.LENGTH_SHORT).show();

//                textView.setText("Your current location is"+ "\n" + "Lattitude = " + latitude
//                        + "\n" + "Longitude = " + longitude);

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
