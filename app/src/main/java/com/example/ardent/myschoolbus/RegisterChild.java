package com.example.ardent.myschoolbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterChild extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegChildSave;
    private EditText editTextRegChildName;
    private EditText editTextRegChildId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_child);

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
    }
}
