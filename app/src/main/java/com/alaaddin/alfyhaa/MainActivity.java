package com.alaaddin.alfyhaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alaaddin.alfyhaa.Authentication.Login;

public class MainActivity extends AppCompatActivity {

    Button buttonlogin ,buttonAboutinst,buttonCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonlogin=(Button)findViewById(R.id.BTN_login);
        buttonAboutinst=(Button)findViewById(R.id.btn_aboutinstit);
        buttonCourses=(Button)findViewById(R.id.btn_cours);


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        buttonAboutinst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AboutInstut.class);
                startActivity(intent);
            }
        });

        buttonCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Courses.class);
                startActivity(intent);
            }
        });

    }
}
