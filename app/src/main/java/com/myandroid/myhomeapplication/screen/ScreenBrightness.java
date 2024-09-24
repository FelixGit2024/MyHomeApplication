package com.myandroid.myhomeapplication.screen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myandroid.myhomeapplication.R;

public class ScreenBrightness extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnPermission, btnCheck, btnClose, btnOpen, btnSet;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_brightness);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnPermission = findViewById(R.id.btn_permission);
        btnCheck = findViewById(R.id.btn_check);
        btnClose = findViewById(R.id.btn_close);
        btnOpen = findViewById(R.id.btn_open);
        btnSet = findViewById(R.id.btn_set);

        editText = findViewById(R.id.edit_number);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentScreenBrightness = getCurrentScreenBrightness();
                Log.i(TAG, "onClick: currentScreenBrightness" + currentScreenBrightness);
                Toast.makeText(ScreenBrightness.this, "当前屏幕亮度->"+currentScreenBrightness, Toast.LENGTH_SHORT).show();
                String s = editText.getText().toString();
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(ScreenBrightness.this, "请输入0-255的数值", Toast.LENGTH_SHORT).show();
                    return;
                }
                int i = Integer.parseInt(s);
                if (i < 0 || i > 255) {
                    Toast.makeText(ScreenBrightness.this, "请输入0-255的数值", Toast.LENGTH_SHORT).show();
                    return;
                }
                setScreenBrightness(i);
                Toast.makeText(ScreenBrightness.this, "设置后屏幕亮度->"+i, Toast.LENGTH_SHORT).show();

            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutoScreenBrightness();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAutoScreenBrightness();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScreenBrightness.this, isAutoScreenBrightness() ? "自动亮度" : "未设置自动亮度", Toast.LENGTH_SHORT).show();
            }
        });

        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanWrite()){
                    Toast.makeText(ScreenBrightness.this, "申请修改系统权限成功", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(ScreenBrightness.this, "没有修改系统权限，请设置", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS));
                }
            }
        });
    }
    //获取当前屏幕亮度
    private int getCurrentScreenBrightness() {
        try {
            int anInt = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            return anInt;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //设置当前屏幕亮度
    private void setScreenBrightness(int brightness) {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
    }


    //打开自动亮度
    private void openAutoScreenBrightness() {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }
    //关闭自动亮度
    private void closeAutoScreenBrightness() {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    //判断是否是自动亮度
    private boolean isAutoScreenBrightness() {
        try {
            int mode = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            return mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    //判断是否申请到WRITE_SETTINGS权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isCanWrite() {
        return Settings.System.canWrite(this);
    }
}