package com.example.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homework.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DatesAdapter extends RecyclerView.Adapter <DatesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> agentName, companyName, dateWork, dateReminder, title;

    public DatesAdapter(Context context, ArrayList<String> agentName, ArrayList<String> companyName, ArrayList<String> dateWork, ArrayList<String> dateReminder, ArrayList<String> title){
        this.context =context;
        this.agentName = agentName;
        this.companyName = companyName;
        this.dateWork = dateWork;
        this.dateReminder = dateReminder;
        this.title = title;
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
        holder.title.setText(String.valueOf(title.get(position)));
        holder.dateReminder.setText(String.valueOf(dateReminder.get(position)));
        holder.dateWork.setText(String.valueOf(dateWork.get(position)));
        holder.agentName.setText(String.valueOf(agentName.get(position)));
        holder.companyName.setText(String.valueOf(companyName.get(position)));
    }

    @Override
    public int getItemCount() {
        return dateWork.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView  agentName, companyName, dateWork, dateReminder, title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_date);
            agentName = itemView.findViewById(R.id.agent_name_date_txt);
            companyName = itemView.findViewById(R.id.company_name_date_txt);
            dateWork = itemView.findViewById(R.id.date_work_txt);
            dateReminder = itemView.findViewById(R.id.date_reminder);
        }
    }
}
