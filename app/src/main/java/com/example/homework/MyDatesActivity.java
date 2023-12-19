package com.example.homework;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


import com.example.homework.adapter.AgentSpinnerAdapter;
import com.example.homework.adapter.DatesAdapter;
import com.example.homework.database.AgentDatabase;
import com.example.homework.database.CompanyDatabase;
import com.example.homework.database.MyDatesDatabase;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MyDatesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    MyDatesDatabase db;
    AgentDatabase agentDatabase;
    CompanyDatabase companyDatabase;

    EditText agentName, companyName, dateWork, dateReminder, title;

    DatesAdapter dateAdapter;
    RelativeLayout layout;
    Button saveData, close;
    CardView openDatesAddPopup;

    ArrayList<String> dateWorkArray, dateReminderArray, titleArray, agentFirstNameArray, agentLastNameArray, companyNameArray;
    ArrayList<Boolean> dateIsDoneArray;
    ArrayList<Integer> companyArray, agentArray;


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
        setContentView(R.layout.my_dates_list);

        db = new MyDatesDatabase(MyDatesActivity.this);
        agentDatabase = new AgentDatabase(MyDatesActivity.this);
        companyDatabase = new CompanyDatabase(MyDatesActivity.this);
        titleArray = new ArrayList<>();
        agentArray = new ArrayList<>();
        companyArray = new ArrayList<>();
        dateReminderArray = new ArrayList<>();
        dateWorkArray = new ArrayList<>();
        dateIsDoneArray = new ArrayList<>();
        companyNameArray = new ArrayList<>();
        agentFirstNameArray = new ArrayList<>();
        agentLastNameArray = new ArrayList<>();
        recyclerView = findViewById(R.id.Recycler_Date);
        openDatesAddPopup = findViewById(R.id.openDateAddPopup);

        storeDataInArray();

        dateAdapter = new DatesAdapter(MyDatesActivity.this, agentArray, companyArray, dateWorkArray, dateReminderArray, titleArray
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
        Cursor cursor = db.readDates();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                titleArray.add(cursor.getString(1));
                companyArray.add(cursor.getInt(2));
                agentArray.add(cursor.getInt(3));
                dateReminderArray.add(cursor.getString(4));
                dateWorkArray.add(cursor.getString(5));
                Boolean value = cursor.getInt(6) > 0;
                dateIsDoneArray.add(value);
            }
        }
    }

    void createPopUpWindow() {
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
                    }
        );

//        companyName = popUpView3.findViewById(R.id.edt_txt_company);
//        agentName = popUpView3.findViewById(R.id.edt_txt_agent);
        dateWork = popUpView3.findViewById(R.id.edt_txt_date);
        dateReminder = popUpView3.findViewById(R.id.edt_txt_date_reminder);
        title = popUpView3.findViewById(R.id.edt_txt_title);
        saveData = popUpView3.findViewById(R.id.saveDateToDatabase);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatesDatabase myDB = new MyDatesDatabase(MyDatesActivity.this);
//                String query = "SELECT _id FROM company_table WHERE name IS " + companyName;

                myDB.addDate(0,
                        0,
                        dateWork.getText().toString().trim(),
                        dateReminder.getText().toString().trim(),
                        title.getText().toString().trim(),
                        false
                );
                popupWindow3.dismiss();
                recreate();
            }
        });


        agentSpinner = popUpView3.findViewById(R.id.agent_spinner);
        Cursor cursor = agentDatabase.readAgent();
        while (cursor.moveToNext()) {
            agentFirstNameArray.add(cursor.getString(1));
            agentLastNameArray.add(cursor.getString(2));
        }
        agentSpinnerAdapter = new AgentSpinnerAdapter(MyDatesActivity.this, agentFirstNameArray, agentLastNameArray);
        agentSpinner.setAdapter(agentSpinnerAdapter);

        agentSpinner.setOnItemSelectedListener(this);

        companySpinner = popUpView3.findViewById(R.id.company_spinner);
        Cursor cursor1 = companyDatabase.readCompany();
        while (cursor1.moveToNext()){
            companyNameArray.add(cursor1.getString(1));
        }
        ArrayAdapter companyArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, companyNameArray);
        companyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companySpinner.setAdapter(companyArrayAdapter);

        companySpinner.setOnItemSelectedListener(this);



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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.agent_spinner){
            Object value = parent.getItemAtPosition(position);
            //storage the value
//            System.out.println(agentFirstNameArray.get(Integer.parseInt(value.toString())));
        }

        if(parent.getId() == R.id.company_spinner){
            String value = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}