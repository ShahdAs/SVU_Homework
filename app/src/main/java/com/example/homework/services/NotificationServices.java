package com.example.homework.services;

import android.content.Context;

import com.example.homework.R;

import androidx.core.app.NotificationCompat;

public class NotificationServices {
    private Context context;
   public NotificationServices(Context context){
       this.context =context;
   }

    void createNotification(String title,String body){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "88")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
