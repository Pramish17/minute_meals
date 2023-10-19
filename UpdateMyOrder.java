package com.implicit.minutemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMyOrder extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText id,item,quantity,phone,description;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_order);
        getSupportActionBar().setTitle("Update Order");
        myDb=new DatabaseHelper(this);
        id=(EditText)findViewById(R.id.updateId);
        item=(EditText) findViewById(R.id.updateItem);
        quantity=(EditText) findViewById(R.id.updateQuantity);
        phone=(EditText) findViewById(R.id.updatePhone);
        description=(EditText) findViewById(R.id.updateDescription);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        updateData();
    }

    public void updateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(id.getText().toString().isEmpty() && item.getText().toString().isEmpty() && quantity.getText().toString().isEmpty() && phone.getText().toString().isEmpty() && description.getText().toString().isEmpty()){
                            Toast.makeText(UpdateMyOrder.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                        }

                        else if(id.getText().toString().isEmpty()){
                            Toast.makeText(UpdateMyOrder.this,"Id Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                        }
                        else if(item.getText().toString().isEmpty()){
                            Toast.makeText(UpdateMyOrder.this,"Item Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                        }
                        else if(quantity.getText().toString().isEmpty()){
                            Toast.makeText(UpdateMyOrder.this,"Quantity Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                        }

                        else if(phone.getText().toString().isEmpty()){
                            Toast.makeText(UpdateMyOrder.this,"Phone No. Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                        }

                        else{
                            boolean isUpdated= myDb.updateData(id.getText().toString(),item.getText().toString(),quantity.getText().toString(),phone.getText().toString(),description.getText().toString());
                            Toast.makeText(UpdateMyOrder.this,"Order Updated!!",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UpdateMyOrder.this, MainActivity3.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}