package com.alaaddin.alfyhaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AboutInstut extends AppCompatActivity {

    TextView  about;
    Button location,web_btn,face_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_instut);

         about =(TextView)findViewById(R.id.tV_ABOUT);
        InputStream input = null;
        try {
            input=getAssets().open("institabout.txt");
            int size= input.available();
            byte [] about_txt = new byte[size];
            input.read(about_txt);
            input.close();
            String text =new String(about_txt);
            about.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        face_btn=findViewById(R.id.btn_face);
        web_btn =findViewById(R.id.btn_website);
        location =findViewById(R.id.btn_location);

        web_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent website =new Intent(Intent.ACTION_VIEW);
                website.setData(Uri.parse("https://www.google.com.tr/"));
                if (website.resolveActivity(getPackageManager()) !=null) {
                    startActivity(website);}

            }
        });

        face_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  FACE =new Intent(Intent.ACTION_VIEW);
                FACE.setData(Uri.parse("https://www.facebook.com/alfyhaa.org/"));
                if (FACE.resolveActivity(getPackageManager()) !=null) {
                    startActivity(FACE);}


            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googmap= new Intent(Intent.ACTION_VIEW);
                googmap.setData(Uri.parse("geo:36.2472786,36.556424?z=17"));
                if (googmap.resolveActivity(getPackageManager()) !=null) {
                    startActivity(googmap);}
            }
        });

    }
}
