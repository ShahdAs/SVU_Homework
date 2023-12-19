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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.homework.adapter.DatesAdapter;
import com.example.homework.database.MyDatesDatabase;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MyDatesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatesDatabase db;

    EditText agentName, companyName, dateWork, dateReminder, title;

    DatesAdapter dateAdapter;
    RelativeLayout layout;
    Button saveData, close;
    CardView openAgentAddPopup;

    ArrayList<String> companyArray, agentArray, dateWorkArray, dateReminderArray, titleArray;

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
        titleArray = new ArrayList<>();
        agentArray = new ArrayList<>();
        companyArray = new ArrayList<>();
        dateReminderArray = new ArrayList<>();
        dateWorkArray = new ArrayList<>();
        recyclerView = findViewById(R.id.Recycler_Date);
        openAgentAddPopup = findViewById(R.id.openDateAddPopup);


        storeDataInArray();
        dateAdapter = new DatesAdapter(MyDatesActivity.this, agentArray, companyArray, dateWorkArray, dateReminderArray, titleArray);

        recyclerView.setAdapter(dateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyDatesActivity.this));
        layout = findViewById(R.id.relativeDateList);
        openAgentAddPopup.setOnClickListener(new View.OnClickListener() {
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
//                firstNameArray.add(cursor.getString(1));
//                lastNameArray.add(cursor.getString(2));
//                workArray.add(cursor.getString(3));
//                phoneNumberArray.add(cursor.getString(4));
//                emailArray.add(cursor.getString(5));
            }
        }
    }

    void createPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.date_add_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
                        @Override
                        public void run() {
                            popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
                        }
                    }
        );

        companyName = popUpView.findViewById(R.id.edt_txt_company);
        agentName = popUpView.findViewById(R.id.edt_txt_agent);
        dateWork = popUpView.findViewById(R.id.edt_txt_date);
        dateReminder = popUpView.findViewById(R.id.edt_txt_date_reminder);
        title = popUpView.findViewById(R.id.edt_txt_title);
        saveData = popUpView.findViewById(R.id.saveDateToDatabase);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatesDatabase myDB = new MyDatesDatabase(MyDatesActivity.this);
//                myDB.addDate(firstName.getText().toString().trim(),
//                        lastName.getText().toString().trim(),
//                        work.getText().toString().trim(),
//                        phoneNumber.getText().toString().trim(),
//                        email.getText().toString().trim());
//                popupWindow.dismiss();
                recreate();
            }
        });

        close = popUpView.findViewById(R.id.closeDatePopup);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }
}