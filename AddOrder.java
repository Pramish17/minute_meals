package com.implicit.minutemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddOrder extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText item,quantity,phone,description;
    Button btnPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getSupportActionBar().setTitle("Add Order");
        myDb=new DatabaseHelper(this);

        item=(EditText) findViewById(R.id.Item);
        quantity=(EditText) findViewById(R.id.quantity);
        phone=(EditText) findViewById(R.id.phone);
        description=(EditText) findViewById(R.id.description);
        btnPlaceOrder=(Button)findViewById(R.id.btnPlaceOrder);
        addData();

    }

    public void  addData(){
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                if(item.getText().toString().isEmpty() && quantity.getText().toString().isEmpty() && phone.getText().toString().isEmpty() && description.getText().toString().isEmpty()){
                    Toast.makeText(AddOrder.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                }

                else if(item.getText().toString().isEmpty()){
                    Toast.makeText(AddOrder.this,"Item Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                }
                else if(quantity.getText().toString().isEmpty()){
                    Toast.makeText(AddOrder.this,"Quantity Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                }

                else if(phone.getText().toString().isEmpty()){
                    Toast.makeText(AddOrder.this,"Phone No. Field Cannot Be Empty!",Toast.LENGTH_LONG).show();
                }

                else{
                    boolean isInserted= myDb.insertData(item.getText().toString(),quantity.getText().toString(),phone.getText().toString(),description.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(AddOrder.this, "Order Placed!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddOrder.this, MainActivity3.class);
                        startActivity(intent);
                    }
                }


            }
        });
    }
}