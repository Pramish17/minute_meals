package com.implicit.minutemeals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewMyOrder extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("View Order");
        setContentView(R.layout.activity_view_my_order);
        myDb=new DatabaseHelper(this);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        viewAll();
    }
    public void viewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = myDb.getAllData();
                if(result.getCount() == 0){
                    showMessage("Sorry","You don't have any orders yet!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("id:" + result.getString(0)+"\n");
                    buffer.append("Item:" + result.getString(1)+"\n");
                    buffer.append("quantity:" + result.getString(2)+"\n");
                    buffer.append("phone:" + result.getString(3)+"\n");
                    buffer.append("description:" + result.getString(4)+"\n\n");

                }
                showMessage("Your Orders are : ",buffer.toString());
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}