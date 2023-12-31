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


import com.example.homework.adapter.CompanyAdapter;
import com.example.homework.database.CompanyDatabase;
import com.example.homework.database.model.CompanyModel;

import java.util.ArrayList;


public class CompanyListActivity extends AppCompatActivity {
    EditText name, location, phoneNumber, email;
    RecyclerView recyclerView;
    CompanyDatabase db;
    CompanyAdapter companyAdapter;
    RelativeLayout layout;
    Button saveCompanyData, closeCompanyPopup;
    CardView openCompanyAddPopup;

    ArrayList<String> nameArray,locationArray, phoneNumberArray, emailArray;

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
        setContentView(R.layout.activity_company_list);

        db = new CompanyDatabase(CompanyListActivity.this);
        nameArray = new ArrayList<>();
        locationArray = new ArrayList<>();
        phoneNumberArray = new ArrayList<>();
        emailArray = new ArrayList<>();

        recyclerView = findViewById(R.id.Recycler_Company);
        openCompanyAddPopup = findViewById(R.id.openCompanyAddPopup);


        storeDataInArray();
        companyAdapter = new CompanyAdapter(CompanyListActivity.this, nameArray, locationArray, phoneNumberArray, emailArray);

        recyclerView.setAdapter(companyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CompanyListActivity.this));
        layout = findViewById(R.id.relativeAgentList);
        openCompanyAddPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUpWindow();
                storeDataInArray();
            }
        });
    }

    void storeDataInArray() {
        for(CompanyModel companyModel : db.readCompany()) {
            nameArray.add(companyModel.getName());
            locationArray.add(companyModel.getLocation());
            phoneNumberArray.add(companyModel.getPhoneNumber());
            emailArray.add(companyModel.getEmail());
        }
    }

    @SuppressLint("MissingInflatedId")
    void createPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView2 = inflater.inflate(R.layout.company_add_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow2 = new PopupWindow(popUpView2, width, height, focusable);
        layout.post(new Runnable() {
                        @Override
                        public void run() {
                            popupWindow2.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
                        }
                    }
        );

        name = popUpView2.findViewById(R.id.editText_name_company);
        location = popUpView2.findViewById(R.id.editText_location_company);
        phoneNumber = popUpView2.findViewById(R.id.editText_phoneNumber_company);
        email = popUpView2.findViewById(R.id.editText_email_company);
        saveCompanyData = popUpView2.findViewById(R.id.saveToCompanyDatabase);
        saveCompanyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyDatabase myDB = new CompanyDatabase(CompanyListActivity.this);
                myDB.addCompany(name.getText().toString().trim(),
                        location.getText().toString().trim(),
                        phoneNumber.getText().toString().trim(),
                        email.getText().toString().trim());
                popupWindow2.dismiss();
                recreate();

            }
        });

        closeCompanyPopup = popUpView2.findViewById(R.id.closeCompanyPopup);
        closeCompanyPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        popUpView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow2.dismiss();
                return true;
            }
        });

    }
}