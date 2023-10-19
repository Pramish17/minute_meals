package com.implicit.minutemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteMyOrder extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText id;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Delete Order");
        setContentView(R.layout.activity_delete_my_order);
        myDb=new DatabaseHelper(this);
        id=(EditText)findViewById(R.id.deleteId);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        deleteData();
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRow = myDb.deleteData(id.getText().toString());
                if (deletedRow > 0){
                    Toast.makeText(DeleteMyOrder.this,"Order Deleted Successfully!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DeleteMyOrder.this, MainActivity3.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(DeleteMyOrder.this,"Order Deletion Failed!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}