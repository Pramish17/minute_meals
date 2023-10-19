package com.implicit.minutemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    Button logout;

    public void addOnCLick(View view){
        Intent intent = new Intent(MainActivity3.this, AddOrder.class);
        startActivity(intent);

    }

    public void viewOnClick(View view){
        Intent intent = new Intent(MainActivity3.this, ViewMyOrder.class);
        startActivity(intent);
    }
    public void updateOnClick(View view){
        Intent intent = new Intent(MainActivity3.this, UpdateMyOrder.class);
        startActivity(intent);
    }

    public void deleteOnClick(View view){
        Intent intent = new Intent(MainActivity3.this, DeleteMyOrder.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity2.MES);
        TextView textV = findViewById(R.id.textView);
        textV.setText(message);
        getSupportActionBar().setTitle("Home");
        logout=(Button) findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),Registeration.class);
                startActivity(intent2);
                Toast.makeText(MainActivity3.this,"Thank you for your visit!! ",Toast.LENGTH_LONG).show();

            }
        });
    }



}