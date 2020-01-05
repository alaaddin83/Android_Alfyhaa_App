package com.alaaddin.alfyhaa.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alaaddin.alfyhaa.Database.DatabaseHelper;
import com.alaaddin.alfyhaa.R;
import com.alaaddin.alfyhaa.Utils;
import com.alaaddin.alfyhaa.models.User;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";

    private EditText edt_e_mail, edt_pasword;
    private TextView txt_warning, txt_note, txt_license;
    private Button btn_login;

    private DatabaseHelper databaseHelper;

    private LoginUser loginUser;
    private DoesEmailExist  doesEmailExist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLogin();
            }
        });

    }

    private void initViews() {

        edt_e_mail = (EditText) findViewById(R.id.edtTxt_usernameoremail);
        edt_pasword = (EditText) findViewById(R.id.edtTxt_Password);

        txt_warning = (TextView) findViewById(R.id.txtWarning);
        txt_license = (TextView) findViewById(R.id.txtLicense2);
        txt_note = (TextView) findViewById(R.id.txtV_note);

        btn_login = (Button) findViewById(R.id.btn_Login);
    }

    private void initLogin() {
        Log.d(TAG, "initLogin: started");
        String email= edt_e_mail.getText().toString();
        if (!email.equals("")){
            if (!edt_pasword.getText().toString().equals("")){
                txt_warning.setVisibility(View.GONE);
                //TODO: execute async task
                doesEmailExist = new DoesEmailExist();
                doesEmailExist.execute(email);


            }else {
                txt_warning.setVisibility(View.VISIBLE);
                txt_warning.setText("Please enter your Password");
            }

        }else {
            txt_warning.setVisibility(View.VISIBLE);
            txt_warning.setText("Please enter your email");
        }

    }

    private class  DoesEmailExist extends AsyncTask<String,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseHelper= new DatabaseHelper(Login.this);

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                SQLiteDatabase db =databaseHelper.getReadableDatabase();
                Cursor cursor= db.query("users",new String[] {"email"},"email=?",
                        new String[] {strings[0]},null,null,null);
                if (null != cursor){
                    if (cursor.moveToFirst()){
                        cursor.close();
                        db.close();
                        return true;

                    }else {
                        cursor.close();
                        db.close();
                        return false;
                    }

                }else {
                    db.close();
                    return false; //false must  be
                }

            }catch (SQLException e){
               e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                //todo:execute async task
                loginUser = new LoginUser();
                loginUser.execute();
            }else {
                txt_warning.setVisibility(View.VISIBLE);
                txt_warning.setText("There is no such  email with this username, please try again");

            }

        }
    }

    private class LoginUser extends AsyncTask<Void,Void, User> {

        private String email;
        private String password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.email= edt_e_mail.getText().toString();
            this.password= edt_pasword.getText().toString();

        }

        @Override
        protected User doInBackground(Void... voids) {
            try {
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                Cursor cursor= db.query("users",null,"e_mail=? AND password=?",
                        new String[] {email,password},null,null,null);
                if (null !=cursor){
                    if (cursor.moveToFirst()){
                        User user = new User();
                        user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                        user.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
                        //user.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")))user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                        user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        //user.setAdress(cursor.getString(cursor.getColumnIndex("address")));
                       // user.setIdintity(cursor.getString(cursor.getColumnIndex("idintity")));
                        user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                        //user.setInit_date(cursor.getString(cursor.getColumnIndex("init_date")));
                        //user.setFinish_date(cursor.getString(cursor.getColumnIndex("finish_date")));

                        cursor.close();
                        db.close();
                        return user;

                    }else {
                        cursor.close();
                        db.close();
                        return null;

                    }

                    }else {
                    db.close();
                    return null;
                }
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (null != user){
                Utils utils = new Utils(Login.this);
                utils.addUserToSharedPreferences(user);

                Intent intent= new Intent(Login.this, RegistryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }else {
                txt_warning.setVisibility(View.VISIBLE);
                txt_warning.setText("Your password is incorrect");

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != doesEmailExist){
            if (!doesEmailExist.isCancelled()){
                doesEmailExist.cancel(true);
            }
        }

        if (null != loginUser){
            if (!loginUser.isCancelled()){
                loginUser.cancel(true);
            }
        }
    }

}