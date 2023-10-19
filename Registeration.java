package com.implicit.minutemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registeration extends AppCompatActivity {

    EditText username,password,reTypePassword;
    Button btnRegister;
    Button nextPage;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        getSupportActionBar().setTitle("Registeration");

        username=(EditText) findViewById(R.id.registerUsername);
        password=(EditText) findViewById(R.id.registerPassword);
        reTypePassword=(EditText) findViewById(R.id.reTypePassword);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        nextPage=(Button)findViewById(R.id.nextPage);
        db= new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass = password.getText().toString();
                String repass=reTypePassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(Registeration.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    if(pass.equals(repass)) {
                        boolean checkuser = db.checkUsername(user);
                        if (checkuser == false) {
                            Boolean insert = db.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(Registeration.this, "Registered SuccessFul", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Registeration.this, "Regsiter Failed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Registeration.this,"User Already Exist! Please Login!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(Registeration.this, "Password does not match.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}