package com.implicit.minutemeals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;
    TextView info;
    TextView info2;
    DatabaseHelper db;
    Integer counter = 5;

    public static final String MES="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Login Here");

        username= (EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        info=(TextView) findViewById(R.id.info);
        info2=(TextView) findViewById(R.id.info2);
        db= new DatabaseHelper(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity2.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean checkuserpass = db.checkUsernamePassword(user,pass);
                        if (checkuserpass == true) {
                            Toast.makeText(MainActivity2.this, "Login Successful!", Toast.LENGTH_LONG).show();
                            EditText editText1 = findViewById(R.id.username);
                            String message = "Welcome to Minute Meals!!!" + " " + editText1.getText();

                            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                            intent.putExtra(MES, message);
                            startActivity(intent);
                        }

                    else{
                        Toast.makeText(MainActivity2.this,"Invalid Credidentials!",Toast.LENGTH_SHORT).show();


                        }
                    if(checkuserpass == false){
                        counter--;
                        info.setText("No. of attempts remaining : " + String.valueOf(counter));
                        if(counter == 0){
                            btnLogin.setEnabled(false);
                            info2.setText("Login is disabled for you by our security system!");

                        }
                    }


                }

            }
        });
    }
}