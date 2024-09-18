package com.myandroid.myhomeapplication.hardware;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myandroid.myhomeapplication.R;

public class MyKeyEvent extends AppCompatActivity {

    private TextView tvKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_key_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvKey=findViewById(R.id.tv_Key);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int action = event.getAction();
        if (keyCode==KeyEvent.KEYCODE_VOLUME_UP && action== MotionEvent.ACTION_DOWN){
            tvKey.setBackgroundColor(Color.YELLOW);
        }else if (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN && action== MotionEvent.ACTION_DOWN){
            tvKey.setBackgroundColor(Color.WHITE);
        }
        return true;
//        return super.onKeyDown(keyCode, event);
    }
}