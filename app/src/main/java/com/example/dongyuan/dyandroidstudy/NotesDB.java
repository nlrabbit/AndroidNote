package com.example.dongyuan.dyandroidstudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dongyuan on 2017/1/6.
 */

public class NotesDB extends SQLiteOpenHelper{

    public NotesDB(Context context) {
        super(context, "notes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT NOT NULL, time TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
