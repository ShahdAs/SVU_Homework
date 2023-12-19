package com.example.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.database.AgentDatabase;

import java.util.ArrayList;

public class AgentSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> firstName, lastName;

    public AgentSpinnerAdapter(Context context, ArrayList<String> firstName, ArrayList<String> lastName){
        this.context =context;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getCount() {
        return firstName.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView  firstNameText, lastNameText;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_agent_spinner_row, parent, false);
        firstNameText = view.findViewById(R.id.textView_firstName_spinner);
        lastNameText = view.findViewById(R.id.textView_lastName_spinner);

        firstNameText.setText(firstName.get(position));
        lastNameText.setText(lastName.get(position));
        return  view;
    }


}
