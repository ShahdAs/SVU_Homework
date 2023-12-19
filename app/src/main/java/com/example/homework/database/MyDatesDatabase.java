package com.example.homework.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
    private static final String TITLE = "title";




    static String query =
            "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COMPANY_ID + " INTEGER, " +
                    AGENT_ID + " INTEGER, " +
                    DATE_REMINDER + " TEXT, " +
                    DATE_WORK + " TEXT, " +
                    TITLE + " TEXT)";

    public MyDatesDatabase(@Nullable Context context) {
        super(context);
    }

    public void addDate(int agentId, int companyId, String dateWork, String dateReminder, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dates = new ContentValues();

        dates.put(AGENT_ID, agentId);
        dates.put(COMPANY_ID, companyId);
        dates.put(DATE_REMINDER, dateWork);
        dates.put(DATE_WORK, dateWork);
        dates.put(TITLE, title);
        long result = db.insert(TABLE_NAME, null, dates);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor readDates() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
