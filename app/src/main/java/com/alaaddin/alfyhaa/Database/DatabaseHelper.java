package com.alaaddin.alfyhaa.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alaaddin.alfyhaa.models.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";


    public static final String DB_NAME ="alfyhaa_db" ;
    public static final  int  DB_VERSION = 7;

    public static final  String USER_TB_NAME = "users";
    public static final  String Trainers_TB_NAME = "trainers";
    public static final  String STUDENT_TB_NAME = "students";


    public DatabaseHelper(@Nullable Context context) {

        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // table for student
        String createStudentTable= "create table "+STUDENT_TB_NAME+" (_id  integer primary key autoincrement," +
                " first_name text not null,last_name text ,phone integer, e_mail text not null ,age integer," +
                "adress text , idintity  double,user_name text , password text not null)";

        //table for admin and employee
        String createUserTable= "create table "+USER_TB_NAME+" (_id  integer primary key autoincrement," +
                "  first_name text ,last_name text ,phone integer, e_mail text not null," +
                "adress text , idintity  double , password text not null," +
                "init_date DATE ,finish_date DATE)";

        //table for trainer
        String createTrainerTable= "create table "+Trainers_TB_NAME+" (_id  integer primary key autoincrement," +
                "  first_name text not null,last_name text ,phone integer, e_mail text not null," +
                "adress text , idintity  double,user_name text not null, password text not null," +
                "course text, work_hours integer,evaluation text,other  text)";


        db.execSQL(createStudentTable);
        db.execSQL(createUserTable);
        db.execSQL(createTrainerTable);

        insertUser(db);
    }

    private void insertUser(SQLiteDatabase db){
        Log.d(TAG, "insertUser: started");

        ContentValues  values= new ContentValues();
        values.put("first_name","ALAADDIN");
        values.put("e_mail","admin");
        values.put("adress","hatay");
        values.put("password","admin");

        db.insert(USER_TB_NAME,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+USER_TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+STUDENT_TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Trainers_TB_NAME);
        onCreate(db);
    }
}

