package com.example.homework.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CompanyDatabase extends DataBaseManager {

    private Context context;
    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;
     static final String TABLE_NAME = "company_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOCATION = "locaton";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String COLUMN_EMAIL = "email";




//    public AgentDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }
//
//    public AgentDatabase(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }

    static          String query =
            "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_LOCATION + " TEXT, " +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_EMAIL + " TEXT)";

    public CompanyDatabase(@Nullable Context context) {
        super(context);
    }

    public void addCompany(String name,  String location, String phoneNumber, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues company = new ContentValues();

        company.put(COLUMN_NAME, name);
        company.put(COLUMN_LOCATION, location);
        company.put(COLUMN_PHONE_NUMBER, phoneNumber);
        company.put(COLUMN_EMAIL, email);
        long result = db.insert(TABLE_NAME, null, company);
//        if (result == -1) {
//            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//        }

    }

    public Cursor readCompany() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
