package com.example.homework;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.homework.adapter.AgentAdapter;
import com.example.homework.database.AgentDatabase;

import java.util.ArrayList;


public class AgentListActivity extends AppCompatActivity {
    EditText firstName, lastName, work, phoneNumber, email;
    RecyclerView recyclerView;
    AgentDatabase db;
    AgentAdapter agentAdapter;
    RelativeLayout layout;
    Button saveData, close;
    CardView openAgentAddPopup;

    ArrayList<String> firstNameArray, lastNameArray, workArray, phoneNumberArray, emailArray;

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
        setContentView(R.layout.activity_agent_list);

        db = new AgentDatabase(AgentListActivity.this);
        firstNameArray = new ArrayList<>();
        lastNameArray = new ArrayList<>();
        workArray = new ArrayList<>();
        phoneNumberArray = new ArrayList<>();
        emailArray = new ArrayList<>();
        recyclerView = findViewById(R.id.Recycler_Agent);
        openAgentAddPopup = findViewById(R.id.openAgentAddPopup);


        storeDataInArray();
        agentAdapter = new AgentAdapter(AgentListActivity.this, firstNameArray, lastNameArray, workArray, phoneNumberArray, emailArray);

        recyclerView.setAdapter(agentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AgentListActivity.this));
        layout = findViewById(R.id.relativeAgentList);
        openAgentAddPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUpWindow();
                storeDataInArray();
            }
        });
    }

    void storeDataInArray() {
        Cursor cursor = db.readAgent();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                firstNameArray.add(cursor.getString(1));
                lastNameArray.add(cursor.getString(2));
                workArray.add(cursor.getString(3));
                phoneNumberArray.add(cursor.getString(4));
                emailArray.add(cursor.getString(5));
            }
        }
    }

    void createPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.agent_add_popup, null);

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

        firstName = popUpView.findViewById(R.id.editText_firstName);
        lastName = popUpView.findViewById(R.id.editText_lastName);
        work = popUpView.findViewById(R.id.editText_work);
        phoneNumber = popUpView.findViewById(R.id.editText_phoneNumber);
        email = popUpView.findViewById(R.id.editText_email);
        saveData = popUpView.findViewById(R.id.saveToDatabase);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgentDatabase myDB = new AgentDatabase(AgentListActivity.this);
                myDB.addAgent(firstName.getText().toString().trim(),
                        lastName.getText().toString().trim(),
                        work.getText().toString().trim(),
                        phoneNumber.getText().toString().trim(),
                        email.getText().toString().trim());
                popupWindow.dismiss();
                recreate();
            }
        });

        close = popUpView.findViewById(R.id.closeAgentPopup);
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