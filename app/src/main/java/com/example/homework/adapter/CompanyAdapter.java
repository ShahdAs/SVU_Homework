package com.example.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;

import java.util.ArrayList;

public class CompanyAdapter extends RecyclerView.Adapter <CompanyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> name, location, phoneNumber, email;

    public CompanyAdapter(Context context, ArrayList<String> name,  ArrayList<String> location, ArrayList<String> email, ArrayList<String> phoneNumber){
        this.context =context;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_company_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameText.setText(String.valueOf(name.get(position)));
        holder.locationText.setText(String.valueOf(location.get(position)));
        holder.phoneNumberText.setText(String.valueOf(phoneNumber.get(position)));
        holder.emailText.setText(String.valueOf(email.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView  nameText,  locationText, phoneNumberText, emailText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textView_companyName);
            locationText = itemView.findViewById(R.id.textView_companyLocation);
            phoneNumberText = itemView.findViewById(R.id.textView_companyPhoneNumber);
            emailText = itemView.findViewById(R.id.textView_companyEmail);
        }
    }
}
