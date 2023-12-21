package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homework.adapter.AgentAdapter;
import com.example.homework.adapter.DatesAdapter;
import com.example.homework.adapter.MyDateCardAdapter;
import com.example.homework.database.AgentDatabase;
import com.example.homework.database.CompanyDatabase;
import com.example.homework.database.MyDatesDatabase;
import com.example.homework.database.model.AgentModel;
import com.example.homework.database.model.CompanyModel;
import com.example.homework.database.model.DateModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    SharedPreferences sharedPreferences;
    Button myDates;
    Button seeResult, openCompanyActivity;
    MyDatesDatabase myDatesDatabase;
    AgentDatabase agentDatabase;
    CompanyDatabase companyDatabase;

    ArrayList<String>  dateWorkArray,dateDelayedArray, dateReminderArray,  titleArray, agentFirstNameArray,agentLastNameArray, companyNameArray;
    ArrayList<Integer> agentIdArray, companyIdArray;
    ArrayList<Boolean> dateIsDoneArray, dateIsDelayed;

    MyDateCardAdapter myDateCardAdapter;
    RecyclerView recyclerView;

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


        recyclerView = findViewById(R.id.recyclerView_mainActivity);
        dateWorkArray = new ArrayList<>();
        dateReminderArray = new ArrayList<>();
        titleArray = new ArrayList<>();
        agentIdArray = new ArrayList<>();
        companyIdArray = new ArrayList<>();
        dateIsDoneArray = new ArrayList<>();
        dateIsDelayed = new ArrayList<>();
        dateDelayedArray = new ArrayList<>();
        agentFirstNameArray = new ArrayList<>();
        agentLastNameArray = new ArrayList<>();
        companyNameArray = new ArrayList<>();

        myDatesDatabase = new MyDatesDatabase(MainActivity.this);
        agentDatabase = new AgentDatabase(MainActivity.this);
        companyDatabase = new CompanyDatabase(MainActivity.this);

        for (DateModel dateModel: myDatesDatabase.readDates()){
            AgentModel agentModel = agentDatabase.readAgentById(dateModel.getAgentId());
            CompanyModel companyModel = companyDatabase.readCompanyById(dateModel.getCompanyId());

            dateWorkArray.add(dateModel.getDateWork());
            dateReminderArray.add(dateModel.getDateReminder());
            titleArray.add(dateModel.getTitle());
            agentIdArray.add(dateModel.getAgentId());
            companyIdArray.add(dateModel.getCompanyId());
            dateIsDoneArray.add(dateModel.getDateIsDone());
            dateIsDelayed.add(dateModel.getDateIsDelayed());
            dateDelayedArray.add(dateModel.getDateDelayed());
            agentFirstNameArray.add(agentModel.getFirstName());
            agentLastNameArray.add(agentModel.getLastName());
            companyNameArray.add(companyModel.getName());
        }




        myDateCardAdapter = new MyDateCardAdapter(MainActivity.this,
                agentFirstNameArray,
                agentLastNameArray,
                companyNameArray,
                dateWorkArray,
                dateReminderArray,
                titleArray,
                dateIsDoneArray,
                dateIsDelayed,
                dateDelayedArray
        );
        recyclerView.setAdapter(myDateCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingActivity();
            }
        });

        seeResult = findViewById(R.id.seeResult);
        myDates = findViewById(R.id.myDatesBtn);
        seeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgentListActivity();
            }
        });
        myDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyDatesActivity();
            }
        });


        openCompanyActivity = findViewById(R.id.openCompanyActivity);
        openCompanyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCompanyListActivity();
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

    public void openMyDatesActivity(){
        Intent intent = new Intent(this, DatesListActivity.class);
        startActivity(intent);
    }

    public  void  openCompanyListActivity(){
        Intent intent = new Intent(this, CompanyListActivity.class);
        startActivity(intent);
    }
}


