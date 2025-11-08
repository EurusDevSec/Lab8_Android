package com.example.bai3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "login.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE = "credentials";
    private static final String COL_ID = "id";
    private static final String COL_USER = "username";
    private static final String COL_PASS = "password";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER + " TEXT, " + COL_PASS + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }


    public void saveCredentials(String user, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE, null, null);
            ContentValues cv = new ContentValues();
            cv.put(COL_USER, user);
            cv.put(COL_PASS, pass);
            db.insert(TABLE, null, cv);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void clearCredentials() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, null, null);
    }

    public String[] getCredentials() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE, new String[]{COL_USER, COL_PASS}, null, null, null, null, null);
        try {
            if (c != null && c.moveToFirst()) {
                String user = c.getString(0);
                String pass = c.getString(1);
                return new String[]{user, pass};
            }
            return null;
        } finally {
            if (c != null) c.close();
        }
    }
}

