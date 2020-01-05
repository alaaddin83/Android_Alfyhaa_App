package com.alaaddin.alfyhaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AboutCourse extends AppCompatActivity {

    TextView aboutcourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_course);

        aboutcourse =(TextView)findViewById(R.id.tv_courses);
        Intent data = getIntent();
        int textpage =data.getExtras().getInt("course");
        textpage ++ ;


        InputStream input = null;
        try {
            input=getAssets().open( textpage+".txt");
            int size= input.available();
            byte [] about_txt = new byte[size];
            input.read(about_txt);
            input.close();
            String text =new String(about_txt);
            aboutcourse.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
