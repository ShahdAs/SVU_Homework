package com.example.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.database.AgentDatabase;

import java.util.ArrayList;

public class AgentAdapter extends RecyclerView.Adapter <AgentAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> firstName, lastName, work, phoneNumber, email;

    public AgentAdapter(Context context, ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> work, ArrayList<String> email, ArrayList<String> phoneNumber){
        this.context =context;
        this.firstName = firstName;
        this.lastName = lastName;
        this.work = work;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_agent_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.firstNameText.setText(String.valueOf(firstName.get(position)));
        holder.lastNameText.setText(String.valueOf(lastName.get(position)));
        holder.workText.setText(String.valueOf(work.get(position)));
        holder.phoneNumberText.setText(String.valueOf(phoneNumber.get(position)));
        holder.emailText.setText(String.valueOf(email.get(position)));
    }

    @Override
    public int getItemCount() {
        return firstName.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView  firstNameText, lastNameText, workText, phoneNumberText, emailText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameText = itemView.findViewById(R.id.textView_firstName);
            lastNameText = itemView.findViewById(R.id.textView_lastName);
            workText = itemView.findViewById(R.id.textView_work);
            phoneNumberText = itemView.findViewById(R.id.textView_phoneNumber);
            emailText = itemView.findViewById(R.id.textView_email);
        }
    }
}
