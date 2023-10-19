package com.implicit.minutemeals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="food.db";
    public static final String TABLE_NAME="food_table";
    public static final String TABLE_NAME1="users";
    public static final String COL_1="ID";
    public static final String COL_2="ITEM";
    public static final String COL_3="QUANTITY";
    public static final String COL_4="PHONE";
    public static final String COL_5="DESCRIPTION";

    public static final String COL_01="username";
    public static final String COL_02="password";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEM TEXT,QUANTITY INTEGER,PHONE TEXT,DESCRIPTION TEXT)");

        db.execSQL("create table " + TABLE_NAME1+ "(username text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        onCreate(db);
    }

    //new method to insert data
    public boolean insertData(String item, String quantity, String phone, String description){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,quantity);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,description);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result== -1){
            return false;
        }
        else{
            return true;
        }

    }

    //to getAll data
    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from " + TABLE_NAME,null);
        return result;
    }

    //update data
    public boolean updateData(String id, String item, String quantity, String phone, String description){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,quantity);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,description);
        db.update(TABLE_NAME,contentValues,"id=?",new String[]{id});
        return true;
    }

    //delete data
    public Integer deleteData(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    //for table2
    //new method to insert data
    public boolean insertData(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_01,username);
        contentValues.put(COL_02,password);
        long result = db.insert(TABLE_NAME1,null,contentValues);
        if(result== -1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean checkUsername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public  boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
