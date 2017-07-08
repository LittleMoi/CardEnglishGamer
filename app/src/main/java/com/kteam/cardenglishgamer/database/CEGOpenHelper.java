package com.kteam.cardenglishgamer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mo on 2017/7/7.
 * CEG公开数据库帮助类
 */

public class CEGOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_QUESTION = "create table question" +
            "";
    public CEGOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
