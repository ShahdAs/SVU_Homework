package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homework.database.AgentDatabase;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    SharedPreferences sharedPreferences;
    Button seeResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("mode", Context.MODE_PRIVATE);
        String mode = sharedPreferences.getString("Mode", "blue");
        if(mode.equals("blue")){
            setTheme(R.style.AppTheme_blue);
        }else if(mode.equals("green")){
            setTheme(R.style.AppTheme_green);
        }else{
            setTheme(R.style.AppTheme_purple);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingActivity();
            }
        });

        seeResult = findViewById(R.id.seeResult);
        seeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgentListActivity();
            }
        });



    }
    public void openSettingActivity(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void openAgentListActivity(){
        Intent intent = new Intent(this, AgentListActivity.class);
        startActivity(intent);
    }
}


