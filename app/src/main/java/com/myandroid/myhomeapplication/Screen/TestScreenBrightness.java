package com.myandroid.myhomeapplication.Screen;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myandroid.myhomeapplication.R;

public class TestScreenBrightness extends AppCompatActivity {
    private static final int[] brightnessLevels = {255, 204, 153, 102, 51};
    private int currentBrightnessIndex = 0;
    private int initBrightnessIndex;
    private TextView tvId;
    private boolean isActivityVisible=true;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x11){
                setScreenBrightness(brightnessLevels[currentBrightnessIndex%5]);
                tvId.setText(""+brightnessLevels[currentBrightnessIndex%5]);
                currentBrightnessIndex++;
            }
        }
    };
    Runnable runnable=new Runnable(){
        @Override
        public void run() {
            while (isActivityVisible){
                try {
                    Thread.sleep(1000);
                    handler.sendEmptyMessage(0x11);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_screen_brightness);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initBrightnessIndex=getCurrentScreenBrightness();
        tvId=findViewById(R.id.tv_id);
        new Thread(runnable).start();
    }

    private int getCurrentScreenBrightness(){
        try {
            int anInt = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            return anInt;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setScreenBrightness(int brightness) {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    @Override
    protected void onPause() {
        isActivityVisible = false;
        super.onPause();
    }

    @Override
    protected void onRestart() {
        isActivityVisible = true;
        new Thread(runnable);
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        isActivityVisible=false;
        setScreenBrightness(initBrightnessIndex);
        super.onDestroy();
    }
}