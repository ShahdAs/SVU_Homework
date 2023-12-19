package com.example.homework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;
    public DataBaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AgentDatabase.query);
        db.execSQL(CompanyDatabase.query);
        db.execSQL(MyDatesDatabase.query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AgentDatabase.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CompanyDatabase.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MyDatesDatabase.TABLE_NAME);
        onCreate(db);
    }
}
