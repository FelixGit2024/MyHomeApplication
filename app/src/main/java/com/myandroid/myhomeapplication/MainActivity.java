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

import com.myandroid.myhomeapplication.audio.MyMediaRecorder;
//import com.myandroid.myhomeapplication.screen.ScreenBrightness;
//import com.myandroid.myhomeapplication.screen.TestScreenBrightness;
//import com.myandroid.myhomeapplication.screen.TouchImage;
//import com.myandroid.myhomeapplication.screen.VolumeKeysControl;
import com.myandroid.myhomeapplication.adapt.TaskAdapt;
import com.myandroid.myhomeapplication.hardware.MyKeyEvent;
import com.myandroid.myhomeapplication.hardware.MyVibrator;
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
        taskModels.add(new TaskModel("  2  ","循环改变屏幕亮度"));
        taskModels.add(new TaskModel("  3  ","音量键控制图片切换"));
        taskModels.add(new TaskModel("  4  ","手指拖动图片位置"));
        taskModels.add(new TaskModel("  5  ","音量键控制文本框高亮"));
        taskModels.add(new TaskModel("  6  ","振动器基本使用"));
        taskModels.add(new TaskModel("  7  ","录音器"));
        TaskAdapt taskAdapt = new TaskAdapt(this, R.layout.activity_main_listitem, taskModels);
        listView.setAdapter(taskAdapt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskModel taskModel = taskModels.get(position);
                if("  1  ".equals(taskModel.getId())){
//                    Intent intent = new Intent(MainActivity.this, ScreenBrightness.class);
//                    startActivity(intent);
                } else if ("  2  ".equals(taskModel.getId())) {
//                    Intent intent = new Intent(MainActivity.this, TestScreenBrightness.class);
//                    startActivity(intent);
                } else if ("  3  ".equals(taskModel.getId())) {
//                    Intent intent = new Intent(MainActivity.this, VolumeKeysControl.class);
//                    startActivity(intent);
                }else if ("  4  ".equals(taskModel.getId())) {
//                    Intent intent = new Intent(MainActivity.this, TouchImage.class);
//                    startActivity(intent);
                }else if ("  5  ".equals(taskModel.getId())) {
                    Intent intent = new Intent(MainActivity.this, MyKeyEvent.class);
                    startActivity(intent);
                }else if ("  6  ".equals(taskModel.getId())) {
                    Intent intent = new Intent(MainActivity.this, MyVibrator.class);
                    startActivity(intent);
                }else if ("  7  ".equals(taskModel.getId())) {
                    Intent intent = new Intent(MainActivity.this, MyMediaRecorder.class);
                    startActivity(intent);
                }
            }
        });

    }
}