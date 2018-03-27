package com.example.a67371.tabtest.ui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
    public MyOpenHelper(Context context) {
        super(context, "lol.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists lol(_id integer primary key autoincrement,name varchar(20))");
        for (int x=0;x<20;x++){
            db.execSQL("insert into lol(name) values(?)",new String[]{"英雄联盟"+x});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
