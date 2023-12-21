package com.example.homework;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import com.example.homework.adapter.AgentSpinnerAdapter;
import com.example.homework.adapter.DatesAdapter;
import com.example.homework.database.AgentDatabase;
import com.example.homework.database.CompanyDatabase;
import com.example.homework.database.MyDatesDatabase;
import com.example.homework.database.model.AgentModel;
import com.example.homework.database.model.CompanyModel;
import com.example.homework.database.model.DateModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MyDatesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatesDatabase db;
    AgentDatabase agentDatabase;
    CompanyDatabase companyDatabase;

    EditText agentName, companyName, dateWork, dateReminder, title;

    DatesAdapter dateAdapter;
    RelativeLayout layout;
    Button saveData, close;
    CardView openDatesAddPopup;

    ArrayList<String> agentFirstNameArray, agentLastNameArray, companyNameArray, dateWorkArray, dateReminderArray, dateDelayedArray, titleArray;
    ArrayList<Boolean> dateIsDoneArray, dateIsDelayedArray;
    ArrayList<Integer> companyId, agentId, dateAgentIdArray, dateCompanyIdArray;


    Spinner agentSpinner, companySpinner;
    AgentSpinnerAdapter agentSpinnerAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences("mode", Context.MODE_PRIVATE);
        String mode = sharedPreferences.getString("Mode", "blue");
        if (mode.equals("blue")) {
            setTheme(R.style.AppTheme_blue);
        } else if (mode.equals("green")) {
            setTheme(R.style.AppTheme_green);
        } else {
            setTheme(R.style.AppTheme_purple);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_list);


        db = new MyDatesDatabase(MyDatesActivity.this);
        agentDatabase = new AgentDatabase(MyDatesActivity.this);
        companyDatabase = new CompanyDatabase(MyDatesActivity.this);
        titleArray = new ArrayList<>();
        dateAgentIdArray = new ArrayList<>();
        dateCompanyIdArray = new ArrayList<>();
        agentFirstNameArray = new ArrayList<>();
        agentLastNameArray = new ArrayList<>();
        companyNameArray = new ArrayList<>();
        dateReminderArray = new ArrayList<>();
        dateWorkArray = new ArrayList<>();
        dateDelayedArray = new ArrayList<>();
        dateIsDoneArray = new ArrayList<>();
        dateIsDelayedArray = new ArrayList<>();
        companyId = new ArrayList<>();
        agentId = new ArrayList<>();
        recyclerView = findViewById(R.id.Recycler_Date);
        openDatesAddPopup = findViewById(R.id.openDateAddPopup);




        storeDataInArray();

        dateAdapter = new DatesAdapter(MyDatesActivity.this, dateAgentIdArray, dateCompanyIdArray, dateWorkArray, dateReminderArray, titleArray
//                , dateIsDoneArray
        );

        recyclerView.setAdapter(dateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyDatesActivity.this));
        layout = findViewById(R.id.relativeDateList);
        openDatesAddPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUpWindow();
                storeDataInArray();
            }
        });


    }

    void storeDataInArray() {
        for (DateModel dateModel : db.readDates()) {
            titleArray.add(dateModel.getTitle());
//                    companyNameArray.add(dateModel.);
//                    agentFirstNameArray.add(cursor.getInt(3));
//                    agentLastNameArray.add(cursor.getInt(3));
            dateReminderArray.add(dateModel.getDateReminder());
            dateWorkArray.add(dateModel.getDateWork());
            dateDelayedArray.add(dateModel.getDateDelayed());
            dateIsDoneArray.add(dateModel.getDateIsDone());
            dateIsDelayedArray.add(dateModel.getDateIsDelayed());
            dateCompanyIdArray.add(dateModel.getCompanyId());
            dateAgentIdArray.add(dateModel.getAgentId());

        }
    }
        void createPopUpWindow () {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popUpView3 = inflater.inflate(R.layout.date_add_popup, null);

            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            boolean focusable = true;
            PopupWindow popupWindow3 = new PopupWindow(popUpView3, width, height, focusable);
            layout.post(new Runnable() {
                @Override
                public void run() {
                    popupWindow3.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
                }
            });

//        companyName = popUpView3.findViewById(R.id.edt_txt_company);
//        agentName = popUpView3.findViewById(R.id.edt_txt_agent);
            dateWork = popUpView3.findViewById(R.id.edt_txt_date);
            dateReminder = popUpView3.findViewById(R.id.edt_txt_date_reminder);
            title = popUpView3.findViewById(R.id.edt_txt_title);


            agentSpinner = popUpView3.findViewById(R.id.agent_spinner);
            for (AgentModel agentModel : agentDatabase.readAgent()) {
                agentFirstNameArray.add(agentModel.getFirstName());
                agentLastNameArray.add(agentModel.getLastName());
                agentId.add(agentModel.getId());
            }
            agentSpinnerAdapter = new AgentSpinnerAdapter(MyDatesActivity.this, agentFirstNameArray, agentLastNameArray);
            agentSpinner.setAdapter(agentSpinnerAdapter);



            companySpinner = popUpView3.findViewById(R.id.company_spinner);
            for(CompanyModel companyModel : companyDatabase.readCompany()) {
                companyNameArray.add(companyModel.getName());
                companyId.add(companyModel.getId());
            }
            ArrayAdapter companyArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, companyNameArray);
            companyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            companySpinner.setAdapter(companyArrayAdapter);



            saveData = popUpView3.findViewById(R.id.saveDateToDatabase);
            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDatesDatabase myDB = new MyDatesDatabase(MyDatesActivity.this);

                    myDB.addDate(agentId.get(agentSpinner.getSelectedItemPosition()),
                            companyId.get(companySpinner.getSelectedItemPosition()),
                            dateWork.getText().toString().trim(),
                            dateReminder.getText().toString().trim(),
                            title.getText().toString().trim()
                    );
                    popupWindow3.dismiss();
                    recreate();
                }
            });


            close = popUpView3.findViewById(R.id.closeDatePopup);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow3.dismiss();
                }
            });
            popUpView3.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow3.dismiss();
                    return true;
                }
            });

        }
    }