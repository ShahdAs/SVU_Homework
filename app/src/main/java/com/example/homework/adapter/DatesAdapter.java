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
    private ArrayList<String>  dateWorkArray, dateReminderArray,  titleArray;
    private  ArrayList<Integer> agentIdArray, companyIdArray;
//    private  ArrayList<Boolean> dateIsDoneArray, dateIsDelayed;


    public DatesAdapter(Context context,
                        ArrayList<Integer> agentId,
                        ArrayList<Integer> companyId,
                        ArrayList<String> dateWork,
                        ArrayList<String> dateReminder,
                        ArrayList<String> title
//            , ArrayList<Boolean> dateIsDone
    ){
        this.context =context;
        this.agentIdArray = agentId;
        this.companyIdArray = companyId;
        this.dateWorkArray = dateWork;
        this.dateReminderArray = dateReminder;
        this.titleArray = title;
//        this.dateIsDoneArray = dateIsDone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_dates_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(String.valueOf(titleArray.get(position)));
        holder.dateReminder.setText(String.valueOf(dateReminderArray.get(position)));
        holder.dateWork.setText(String.valueOf(dateWorkArray.get(position)));
        holder.agentId.setText("" +agentIdArray.get(position));
        holder.companyId.setText(""+companyIdArray.get(position));
        // TODO: add the boolean
    }

    @Override
    public int getItemCount() {
        return titleArray.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView  agentId, companyId, dateWork, dateReminder, title;
//        Boolean dateIsDone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_date);
            agentId = itemView.findViewById(R.id.agent_name_date_txt);
            companyId = itemView.findViewById(R.id.company_name_date_txt);
            dateWork = itemView.findViewById(R.id.date_work_txt);
            dateReminder = itemView.findViewById(R.id.date_reminder);
            // TODO: set the boolean
        }
    }
}
