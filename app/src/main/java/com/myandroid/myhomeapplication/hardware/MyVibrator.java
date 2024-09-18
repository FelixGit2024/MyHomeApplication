package com.myandroid.myhomeapplication.hardware;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myandroid.myhomeapplication.R;

public class MyVibrator extends AppCompatActivity {
    private Button btn_hasVibrator;
    private Button btn_short;
    private Button btn_long;
    private Button btn_rhythm;
    private Button btn_cancle;
    private Vibrator myVibrator;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_vibrator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_hasVibrator = findViewById(R.id.btn_hasVibrator);
        btn_short = findViewById(R.id.btn_short);
        btn_long = findViewById(R.id.btn_long);
        btn_rhythm = findViewById(R.id.btn_rhythm);
        btn_cancle = findViewById(R.id.btn_cancle);
        //获得系统的Vibrator实例:
        myVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        mContext = MyVibrator.this;


        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVibrator.cancel();
                Toast.makeText(mContext, "取消振动", Toast.LENGTH_SHORT).show();
            }
        });

        btn_rhythm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVibrator.cancel();
                myVibrator.vibrate(new long[]{500, 100, 500, 100, 500, 100}, 0);
                Toast.makeText(mContext, "节奏振动", Toast.LENGTH_SHORT).show();
            }
        });

        btn_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVibrator.cancel();
                myVibrator.vibrate(new long[]{100, 100, 100, 1000}, 0);
                Toast.makeText(mContext, "长振动", Toast.LENGTH_SHORT).show();
            }
        });

        btn_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVibrator.cancel();
                myVibrator.vibrate(new long[]{100, 200, 100, 200}, 0);
                Toast.makeText(mContext, "短振动", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hasVibrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, myVibrator.hasVibrator() ? "当前设备有振动器" : "当前设备无振动器",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}