package com.example.class_task_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.sql.StatementEvent;

public class SQLite extends SQLiteOpenHelper {

    private final  String TABLE_NAME = "Login";
    private  final  String COL1 = "Username";
    private  final  String COL2 = "Password";
    private  static final int DATABASE_VERSION = 1;
    private  static final String DATABASE_NAME = "User";

    Context c;

    public SQLite(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COL1 +" TEXT, " + COL2+" TEXT)";
        sqLiteDatabase.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  long SaveData(String Name, String Pass){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, Name);
        cv.put(COL2, Pass);
        long r = db.insert(TABLE_NAME, null,cv);

        return r;
    }

    public  String  ViewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Login", null);
        String record= "";
        while (cursor.moveToNext()){
            String uname = cursor.getString(1);
            String upass = cursor.getString(2);

            record = record + uname +" _ "+ upass + "\n";
        }
        cursor.close();
        return record;
    }

    public  String Search(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME, null);
        String record = "";
        while (cursor.moveToNext()){

            String uname = cursor.getString(1);
            String upass = cursor.getString(2);

            if(uname.equals(name)){
                record = record + uname +" _ "+ upass + "\n";
            }
        }
        return record;
    }

    public boolean UpdateData(String name, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1,name);
        cv.put(COL2,pass);
        long r =0;
        Cursor cursor= db.rawQuery("select * from Login where Username = ?" ,
                new String[]{name} );

        if (cursor.getCount()>0){
            r = db.update("Login", cv, "Username = ?", new String[]{name});
        }
        if (r>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean Delete(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long r =0;
        Cursor cursor= db.rawQuery("select * from Login where Username = ?" ,
                new String[]{name} );

        if(cursor.getCount()>0) {
            r= db.delete("Login", "UserName = ?", new String[]{name});
        }
        if (r>=0){
            return true;
        }
        else {
            return false;
        }
    }
}