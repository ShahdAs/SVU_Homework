package com.example.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.homework.R;
import com.example.homework.database.AgentDatabase;
import com.example.homework.database.CompanyDatabase;
import com.example.homework.database.model.AgentModel;
import com.example.homework.database.model.CompanyModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyDateCardAdapter extends RecyclerView.Adapter <MyDateCardAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String>  dateDelayedArray, dateWorkArray, dateReminderArray,  titleArray, companyNameArray, agentFirstNameArray, agentLastNameArray;
    private  ArrayList<Boolean> dateIsDoneArray, dateIsDelayedArray;


    public MyDateCardAdapter(Context context,
                             ArrayList<String> agentFirstName,
                             ArrayList<String> agentLastName,
                             ArrayList<String> companyNameArray,
                             ArrayList<String> dateWork,
                             ArrayList<String> dateReminder,
                             ArrayList<String> title,
                             ArrayList<Boolean> dateIsDone,
                             ArrayList<Boolean> dateIsDelayed,
                             ArrayList<String> dateDelayed
    ){
        this.context =context;
        this.agentLastNameArray = agentLastName;
        this.agentFirstNameArray = agentFirstName;
        this.companyNameArray = companyNameArray;
        this.dateWorkArray = dateWork;
        this.dateReminderArray = dateReminder;
        this.titleArray = title;
        this.dateIsDoneArray = dateIsDone;
        this.dateIsDelayedArray = dateIsDelayed;
        this.dateDelayedArray = dateDelayed;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_my_date_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(String.valueOf(titleArray.get(position)));
        holder.agentFirstName.setText(String.valueOf(agentFirstNameArray.get(position)));
        holder.agentLastName.setText(String.valueOf(agentLastNameArray.get(position)));
        holder.companyName.setText(String.valueOf(companyNameArray.get(position)));
        holder.dateWork.setText(String.valueOf(dateWorkArray.get(position)));
        holder.dateRemainder.setText(String.valueOf(dateReminderArray.get(position)));


        holder.dateDelayed.setText(String.valueOf(dateDelayedArray.get(position)));
        holder.dateDelayedText.setText(",Delayed from");



    }

    @Override
    public int getItemCount() {
        return titleArray.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView  title, agentFirstName,agentLastName, companyName, dateWork,dateDelayed, dateDelayedText, dateRemainder;
//        Button delayTheDateButton, dateIsDoneButton;
//        CardView doneCheckCard, notDoneCheckCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            agentFirstName = itemView.findViewById(R.id.card_agent_firstname);
            agentLastName = itemView.findViewById(R.id.card_agent_lastname);
            companyName = itemView.findViewById(R.id.card_company_name);
            dateWork = itemView.findViewById(R.id.card_date);
            dateDelayed = itemView.findViewById(R.id.card_delayed_date);
            dateRemainder = itemView.findViewById(R.id.card_remainder_date);
            dateDelayedText= itemView.findViewById(R.id.card_delayed_text);

//            delayTheDateButton= itemView.findViewById(R.id.delay_the_date_btn);
//            dateIsDoneButton= itemView.findViewById(R.id.date_is_done_btn);
//
//            doneCheckCard= itemView.findViewById(R.id.card_date_is_done_check);
//            notDoneCheckCard= itemView.findViewById(R.id.card_date_is_not_done_check);


        }
    }
}
