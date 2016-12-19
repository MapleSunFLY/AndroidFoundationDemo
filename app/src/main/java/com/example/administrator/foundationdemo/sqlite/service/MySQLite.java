package com.example.administrator.foundationdemo.sqlite.service;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/12.
 */
public class MySQLite  extends SQLiteOpenHelper{

    public MySQLite (Context context,String name){//保存路径  <包>/datebases/
        this(context,name,null,2);
    }

    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//数据库第一次被创建时调用
        //创建表                             表名称  自增长ID名
        sqLiteDatabase.execSQL("CREATE TABLE person (personId integer primary key autoincrement,name varchar(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//版本号发生修改时调用
        //给表增加已给字段
        sqLiteDatabase.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");


    }
}
