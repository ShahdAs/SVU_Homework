package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SettingActivity extends AppCompatActivity {
    Button settingButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("mode", Context.MODE_PRIVATE);
        mode = sharedPreferences.getString("Mode", "blue");
        if(mode.equals("blue")){
            setTheme(R.style.AppTheme_blue);
        }else if(mode.equals("green")){
            setTheme(R.style.AppTheme_green);
        }else{
            setTheme(R.style.AppTheme_purple);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageView blue = findViewById(R.id.blue);
        ImageView green = findViewById(R.id.green);
        ImageView purple = findViewById(R.id.purple);
        changeTheme(blue, "blue");
        changeTheme(green, "green");
        changeTheme(purple, "purple");

//        settingButton = findViewById(R.i.change);
//        settingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editor = sharedPreferences.edit();
//                editor.putBoolean("nightMode", !nightMode);
//                editor.apply();
//                recreate();
//            }
//        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        ImageView blue = findViewById(R.id.blue);
        ImageView green = findViewById(R.id.green);
        ImageView purple = findViewById(R.id.purple);
        changeTheme(blue, "blue");
        changeTheme(green, "green");
        changeTheme(purple, "purple");
    }

    void changeTheme(ImageView imageView, String color){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("Mode", color);
                editor.clear().commit();
                recreate();
            }
        });
    }


}
