package com.example.administrator.foundationdemo.sqlite.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


import com.example.administrator.foundationdemo.sqlite.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PersonSQLite {

    private MySQLite mySQLite;
    private Context context;
    public PersonSQLite(Context context){
        this.context = context;
        mySQLite = new MySQLite(context,"mySQLite");
    }

    //增
    public void save(Person person){
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        /**
         * 方法一：Androiod集成API，用于SQLite数据库增加数据
         */
//        ContentValues values = new ContentValues();
//                 //参数键 值
//        values.put("name",person.getName());
//        values.put("phone",person.getPhone());
//                //表名          参数集合
//        db.insert("person",null,values);//NULL值字段，及可以为空的字段，如果db.insert("person","name",null);及name为NULL不会报错，如果均为空着会报错
        /**
         * 方法二：execSQL，SQLite 语句添加，可练习SQLite语句的熟练度
         */
                  //增加的SQLite语句                                          根据前面问号顺序排列的值
        db.execSQL("insert into person (name,phone) values (?,?)",new Object[]{person.getName(),person.getPhone()});
        db.close();//关闭数据库

    }
    //删
    public void delete(Integer id){
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        /**
         * 方法一：Androiod集成API，用于SQLite数据删除数据
         */
//        db.delete("person","personId=?",new String[]{id.toString()});
        /**
         * 方法二：execSQL，SQLite 语句删除，可练习SQLite语句的熟练度
         */
        db.execSQL("delete from person where personId=?",new Object[]{id});
        db.close();//关闭数据库
    }
    //改
    public void update(Person person){
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        /**
         * 方法一：Androiod集成API，用于SQLite数据库更新数据
         */
//        ContentValues values = new ContentValues();
//        //参数键 值
//        values.put("name",person.getName());
//        values.put("phone",person.getPhone());
//        db.update("person", values, "personId=?", new String[]{person.getId()+""});
        /**
         * 方法二：execSQL，SQLite 语句更新，可练习SQLite语句的熟练度
         */
        db.execSQL("update person set name=?,phone=? where personId=?",new Object[]{person.getName(),person.getPhone(),person.getId()});
        db.close();//关闭数据库
    }
    //查
    public Person find(Integer id){
        SQLiteDatabase db = mySQLite.getReadableDatabase();//如果磁盘内存未满着调用getWritableDatabase(),如果磁盘内存满了，就用只读模式开启数据库
        /**
         * 方法一：Androiod集成API，用于SQLite数据库查找数据
         */
                           //数据集合null及全部
//        db.query("person",null, "personId=?", new String[]{id.toString()},null,null,null);
        /**
         * 方法二：execSQL，SQLite 语句查找，可练习SQLite语句的熟练度
         */

        Cursor cursor = db.rawQuery("select * from person where personId=?", new String[]{id.toString()});//获得游标
        if (cursor.moveToFirst()){//判断数据是否存在
            int personId = cursor.getInt(cursor.getColumnIndex("personId"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            cursor.close();
            return new Person(personId,name,phone);
        }else {
            cursor.close();
            Toast.makeText(context,"数据不存在！",Toast.LENGTH_SHORT).show();
            return null;
        }

    }
    //分页查询
    public List<Person> getScrollDate(int offset,int maxResult){

        List<Person> list = new ArrayList<Person>();
        SQLiteDatabase db = mySQLite.getReadableDatabase();//如果磁盘内存未满着调用getWritableDatabase(),如果磁盘内存满了，就用只读模式开启数据库

        /**
         * 方法一：Androiod集成API，用于SQLite数据库查找数据
         */

//        db.query("person",null,null ,null,null,"personId asc",offset+","+maxResult);
        /**
         * 方法二：execSQL，SQLite 语句查找，可练习SQLite语句的熟练度
         */

        //将数据库表person按personId升序排列在分页
        Cursor cursor = db.rawQuery("select * from person order by personId asc limit ?,?", new String[]{String.valueOf(offset),String.valueOf(maxResult)});
        while (cursor.moveToNext()){//移动游标

            int personId = cursor.getInt(cursor.getColumnIndex("personId"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            list.add(new Person(personId,name,phone));
        }
        cursor.close();//关闭指针
        return list;
    }
    //统计数据库记录
    public long getCount(){
        SQLiteDatabase db = mySQLite.getReadableDatabase();//如果磁盘内存未满着调用getWritableDatabase(),如果磁盘内存满了，就用只读模式开启数据库
        /**
         * 方法一：Androiod集成API，用于SQLite数据库查找数据
         */

//        db.query("person",new String[]{"count(*)"},null ,null,null,null,null);
        /**
         * 方法二：execSQL，SQLite 语句查找，可练习SQLite语句的熟练度
         */
        //将数据库表person按personId升序排列在分页
        Cursor cursor = db.rawQuery("select count(*) from person ", null);
        cursor.moveToFirst();
        long result = cursor.getLong(0);
        cursor.close();
        return result;
    }
}
