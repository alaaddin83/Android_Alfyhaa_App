package com.alaaddin.alfyhaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {

    ListView listv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        //listVIEW  CODE
        listv = findViewById(R.id.lv_courses);
        ArrayList<String> COURSE =new ArrayList<>();
        COURSE.add("دورة صيانة الجوالات");
        COURSE.add("دورة برمجة التطبيقات");
        COURSE.add("دورة التصميم والمونتاج");
        COURSE.add("دورة التسويق الالكتروني");
        COURSE.add("دورة تمديد الكهرباء المنزلية");
        COURSE.add("دورة اللغة التركية");
        COURSE.add("دورة إدارة المشاريع");
        COURSE.add("دورة السكرتارية");
        COURSE.add("دورة الشبكات الحاسوبية");
        COURSE.add("دورة صناعة الحلويات");
        ArrayAdapter<String> adapter= new ArrayAdapter<>(Courses.this,
                R.layout.row_course_name,R.id.TV_item, COURSE);
        listv.setAdapter(adapter);

        // تفعيل الانتقال الة الصفحة التالية
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(Courses.this,AboutCourse.class);
                intent.putExtra("course",position);
                startActivity(intent);

            }
        });
    }
}
