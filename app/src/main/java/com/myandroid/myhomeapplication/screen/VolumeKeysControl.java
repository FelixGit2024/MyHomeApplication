package com.myandroid.myhomeapplication.screen;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myandroid.myhomeapplication.R;

public class VolumeKeysControl extends AppCompatActivity {
    private ImageView imageView;
    private int[] image;
    private int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volume_keys_control);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView=findViewById(R.id.ImgView);
        image= new int[]{R.drawable.circle_1,R.drawable.circle_2,R.drawable.circle_3,R.drawable.circle_4,R.drawable.circle_5,R.drawable.circle_6};
        imageView.setImageResource(image[index]);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            index=(index+1)%image.length;
        } else if (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN) {
            index=(index-1+image.length)%image.length;
        }
//        Toast.makeText(this, ""+index, Toast.LENGTH_SHORT).show();
        imageView.setImageResource(image[index]);
        return true;// true会屏蔽音量键原有的功能  false保留原有功能同时实现这部分功能

//        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}