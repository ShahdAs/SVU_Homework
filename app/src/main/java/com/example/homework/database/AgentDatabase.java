package com.example.homework.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AgentDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "agent_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_WORK = "work";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String COLUMN_EMAIL = "email";




    public AgentDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

//    public AgentDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }
//
//    public AgentDatabase(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME + " TEXT, " +
                COLUMN_WORK + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_EMAIL + " TEXT)" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
