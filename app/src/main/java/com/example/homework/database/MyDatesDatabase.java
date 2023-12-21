package com.example.homework.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.homework.database.model.AgentModel;
import com.example.homework.database.model.DateModel;

import java.util.ArrayList;

public class MyDatesDatabase extends DataBaseManager {

    private Context context;
    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "dates_table";
    private static final String COLUMN_ID = "_id";
    private static final String AGENT_ID = "agent_id";
    private static final String COMPANY_ID = "company_id";
    private static final String DATE_REMINDER = "date_reminder";
    private static final String DATE_WORK = "date_work";
    private static final String DATE_DELAYED = "date_delayed";
    private static final String TITLE = "title";
    private static final String DATE_IS_DONE = "date_is_done";
    private static final String DATE_IS_DELAYED = "date_is_delayed";




    static String query =
            "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TITLE + " TEXT," +
                    COMPANY_ID + " INTEGER, " +
                    AGENT_ID + " INTEGER, " +
                    DATE_REMINDER + " TEXT, " +
                    DATE_WORK + " TEXT, " +
                    DATE_DELAYED + " TEXT, " +
                    DATE_IS_DELAYED + " BOOLEAN, " +
                    DATE_IS_DONE + " BOOLEAN)";

    public MyDatesDatabase(@Nullable Context context) {
        super(context);
    }

    public void addDate(int agentId, int companyId, String dateWork, String dateReminder, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dates = new ContentValues();
        dates.put(TITLE, title);
        dates.put(COMPANY_ID, companyId);

        dates.put(TITLE, title);
        dates.put(AGENT_ID, agentId);
        dates.put(DATE_REMINDER, dateReminder);
        dates.put(DATE_WORK, dateWork);
        dates.put(DATE_IS_DONE, false);
        dates.put(DATE_IS_DELAYED, false);
        dates.put(DATE_DELAYED, "kxcnfk,");
        long result = db.insert(TABLE_NAME, null, dates);



    }

    public ArrayList<DateModel> readDates() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DateModel> dateModels = new ArrayList<>();
        DateModel dateModel;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Boolean value1 = cursor.getInt(7) > 0;
                Boolean value2 = cursor.getInt(8) > 0;
                dateModel = new DateModel(
                        (cursor.getInt(0)),
                        (cursor.getInt(3)),
                        (cursor.getInt(2)),
                        (cursor.getString(1)),
                        (cursor.getString(5)),
                        (cursor.getString(4)),
                        (cursor.getString(6)),
                        value1,
                        value2
                );
                dateModels.add(dateModel);
            }
        }
        return dateModels;
    }

//    public void delayDate(String newDate, Integer id){
//        String query "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID
//    }

}
