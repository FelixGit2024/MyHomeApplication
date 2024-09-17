package com.myandroid.myhomeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myandroid.myhomeapplication.Screen.ScreenBrightness;
import com.myandroid.myhomeapplication.Screen.TestScreenBrightness;
import com.myandroid.myhomeapplication.Screen.VolumeKeysControl;
import com.myandroid.myhomeapplication.adapt.TaskAdapt;
import com.myandroid.myhomeapplication.model.TaskModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listView);
        ArrayList<TaskModel> taskModels = new ArrayList<>();
        taskModels.add(new TaskModel("  1  ","屏幕亮度明暗变化"));
        taskModels.add(new TaskModel("1-test","循环改变屏幕亮度"));
        taskModels.add(new TaskModel("2-test","音量键控制图片切换"));
        TaskAdapt taskAdapt = new TaskAdapt(this, R.layout.activity_main_listitem, taskModels);
        listView.setAdapter(taskAdapt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskModel taskModel = taskModels.get(position);
                if("  1  ".equals(taskModel.getId())){
                    Intent intent = new Intent(MainActivity.this, ScreenBrightness.class);
                    startActivity(intent);
                } else if ("1-test".equals(taskModel.getId())) {
                    Intent intent = new Intent(MainActivity.this, TestScreenBrightness.class);
                    startActivity(intent);
                } else if ("2-test".equals(taskModel.getId())) {
                    Intent intent = new Intent(MainActivity.this, VolumeKeysControl.class);
                    startActivity(intent);
                }
            }
        });

    }
}