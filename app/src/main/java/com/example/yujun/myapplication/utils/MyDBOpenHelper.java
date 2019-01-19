package com.example.yujun.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yujun on 2018/1/2.
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {super(context, "system.db", null, 1); }
    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user" +
                "(uid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(20)," +
                "phone VARCHAR(20)," +
                "pwd VARCHAR(20)" +
                ")");
    }
    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("ALTER TABLE user ADD phone VARCHAR(12) NULL");
    }
}
