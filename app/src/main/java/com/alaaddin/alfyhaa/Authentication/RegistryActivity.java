package com.alaaddin.alfyhaa.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.widget.Toast;

import com.alaaddin.alfyhaa.Database.DatabaseHelper;
import com.alaaddin.alfyhaa.R;
import com.alaaddin.alfyhaa.Utils;
import com.alaaddin.alfyhaa.models.User;

public class RegistryActivity extends AppCompatActivity {
    private static final String TAG = "RegistryActivity";

    private EditText edtTxtFIRSTnAME, edtTxtEmail, edtTxtPassword, edtTxtAddress, edtTxtdepartment;
    private TextView txtWarning, txtLicense;
    private Button btnRegister;

    private DoesUserExist doesUserExist;
    private RegisterUser  registerUser;

    private DatabaseHelper databaseHelper;

    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);


        initViews();

        utils = new Utils(this);
        User user = utils.isUserLoggedIn();

        if (null != user){
            Toast.makeText(this, "User"+user.getFirst_name()+"logged in", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent =new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

        databaseHelper =  new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });


    }
    private void initViews() {

        edtTxtFIRSTnAME=(EditText) findViewById(R.id.edtText_first_name);
        edtTxtEmail = (EditText) findViewById(R.id.edtTextEmail);
        edtTxtPassword = (EditText) findViewById(R.id.edtTextpassword);
        edtTxtAddress = (EditText) findViewById(R.id.edtTextAddress);
        edtTxtdepartment = (EditText) findViewById(R.id.edtTextname);

        txtWarning = (TextView) findViewById(R.id.txtWarning);
        txtLicense = (TextView) findViewById(R.id.txtLicense);


        btnRegister = (Button) findViewById(R.id.btnRegister);


    }

    private void initRegister () {

        String first_name = edtTxtFIRSTnAME.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String password = edtTxtPassword.getText().toString();
        String department = edtTxtdepartment.getText().toString();
        String address = edtTxtAddress.getText().toString();

        if (email.equals("") || password.equals("") || first_name.equals("")) {
            txtWarning.setVisibility(View.VISIBLE);
            txtWarning.setText("please enter the password and email  and first name");
        } else {
            txtWarning.setVisibility(View.GONE);

            doesUserExist = new DoesUserExist();
            doesUserExist.execute(email);

        }

    }

    private  class DoesUserExist extends AsyncTask<String,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseHelper= new DatabaseHelper(RegistryActivity.this);

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                SQLiteDatabase db =databaseHelper.getReadableDatabase();
                Cursor cursor= db.query("users",new String[] {"_id","email"},"email=?",
                        new String[] {strings[0]},null,null,null);

                if (null != cursor){
                    if (cursor.moveToFirst()){
                        if (cursor.getString(cursor.getColumnIndex("email")).equals(strings[0])){
                            cursor.close();
                            db.close();
                            return true;
                        }else {
                            cursor.close();
                            db.close();
                            return false;
                        }

                    }else {
                        cursor.close();
                        db.close();
                        return false;
                    }
                }else {
                    db.close();
                    return false;
                }

            }catch (SQLException e){
                //SQLExeption in android not in java
                e.printStackTrace();
                return true;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean){
                txtWarning.setVisibility(View.VISIBLE);
                txtWarning.setText("There is user with this email, please try another email");
            }else {
                txtWarning.setVisibility(View.GONE);

                registerUser = new RegisterUser();
                registerUser.execute();
            }
        }
    }

    private class RegisterUser extends AsyncTask<Void,Void, User>{

        private String first_name;
        private String email;
        private String password;
        private String user_name;
        private String address;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            String firstname = edtTxtFIRSTnAME.getText().toString();
            String email = edtTxtEmail.getText().toString();
            String password = edtTxtPassword.getText().toString();
//            String username = edtTxtName.getText().toString();
            String address = edtTxtAddress.getText().toString();

            this.first_name= firstname;
            this.email=email;
            this.password=password;
//            this.user_name= username;
            this.address=address;
        }

        @Override
        protected User doInBackground(Void... voids) {
            try {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                ContentValues values=new ContentValues();
                values.put("first_name",this.first_name);
                values.put("email",this.email);
                values.put("password",this.password);
                values.put("address",this.address);
//                values.put("user_name",this.user_name);

                long userId = db.insert("users",null, values);
                Log.d(TAG, "doInBackground: userId");

                //add user to shared preferance that better for another login to app
                Cursor cursor= db.query("users",null,"_id=?",
                        new String[] {String.valueOf(userId)},null,null,null);

                if (null != cursor){
                    if (cursor.moveToFirst()){
                        User user = new User();
                        user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                        user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                        user.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
                        //user.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                        user.setAdress(cursor.getString(cursor.getColumnIndex("address")));
                        //user.setImage_url(cursor.getString(cursor.getColumnIndex("image_url")));

                        cursor.close();
                        db.close();
                        return user;
                    }else {
                        cursor.close();
                        db.close();
                        return null;
                    }

                }else  {
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
                Toast.makeText(RegistryActivity.this, "User "+user.getEmail()+
                        " registred successfully", Toast.LENGTH_SHORT).show();
                Utils utils = new Utils(RegistryActivity.this);
                utils.addUserToSharedPreferences(user);
                Intent intent= new Intent(RegistryActivity.this, AllRegistryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else {
                Toast.makeText(RegistryActivity.this, "wasn,t able  to register,please try again later",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != doesUserExist){
            if (!doesUserExist.isCancelled()){
                doesUserExist.cancel(true);
            }
        }

        if (null != registerUser){
            if (!registerUser.isCancelled()){
                registerUser.cancel(true);
            }
        }
    }
}

