package com.alaaddin.alfyhaa;

import android.content.Context;
import android.content.SharedPreferences;

import com.alaaddin.alfyhaa.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Utils {


    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public void addUserToSharedPreferences (User user){
        SharedPreferences sharedPreferences= context.getSharedPreferences("logged_in_user",Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor= sharedPreferences.edit();

        Gson gson = new Gson();

        editor.putString("user",gson.toJson(user));
        editor.apply();

    }

    public User isUserLoggedIn(){
        SharedPreferences sharedPreferences= context.getSharedPreferences("logged_in_user",Context.MODE_PRIVATE);
        Gson gson = new Gson();

        //to convert from gson  to java file
        Type type= new TypeToken<User>(){}.getType();
        User user = gson.fromJson(sharedPreferences.getString("user",null),type);
        return user;
    }

}
