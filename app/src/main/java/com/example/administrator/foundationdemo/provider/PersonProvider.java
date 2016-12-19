package com.example.administrator.foundationdemo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.administrator.foundationdemo.sqlite.service.MySQLite;

/**
 * Created by Administrator on 2016/12/14.
 */
public class PersonProvider extends ContentProvider {


    /** Uri讲解：
     *  Uri代表要操作的数据，Uri主要包含了两部分信息：
     *  1.需要操作的ContentProvider
     *  2.对ContentProvider中的什么数据进行操作
     *  一个Uri由以下几部分组成：
     *  content://com.example.administrator.foundationdemo.provider.PersonProvider/person/10
     *  |-scheme-|------------主机名或authority（唯一标识）----------------------|-路径-|-ID-|
     *  ContentProvider(类容提供者)的scheme已经由Android所规定，scheme为：content://
     *  主机名（或叫Authority）用于唯一标识这个ContentProvider，外部调用者可以根据这个标识找到他调用共享数据
     *  路径（path）可以用来表示我们要操作的数据，路径结构根据实际情况而定，如下：
     *  要操作person表中id为10的记录，可以这样构建路径：/person/10
     *  要操作person表中id为10的记录的name字段，可以这样构建路径：/person/10/name
     *  要操作那个表XXX,那么机构路径就为：/XXX
     *  将字符串转换成Uri
     *  调用方法：Uri uri = Uri.parse("string")
     */

    private MySQLite mySQLite;
    //用于判断Uri是否匹配类
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    //匹配码
    private static final int PERSONS = 1;
    private static final int PERSON = 2;
    static {
        //添加待匹配的Uri    主机名（或叫Authority）                                         //路径   匹配码
        URI_MATCHER.addURI("com.example.administrator.foundationdemo.provider.PersonProvider","person",PERSONS);
        //添加待匹配的Uri    主机名（或叫Authority）                                         //路径 #代表未知数字   匹配码
        URI_MATCHER.addURI("com.example.administrator.foundationdemo.provider.PersonProvider","person/#",PERSON);
    }
    @Override
    public boolean onCreate() {
        //ContentProvider实例被创建时系统调用
        mySQLite = new MySQLite(this.getContext(),"mySQLite");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] columns, String whereClause, String[] whereArgs, String orderBy) {
        //供外部应用查询ContentProvider内的数据
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        //操作ContentProvider数据前，先判断Uri是否匹配，判断scheme与路径是否是要操作的表的路径
        switch (URI_MATCHER.match(uri)){
            case PERSONS:
                return db.query("person",columns, whereClause,whereArgs,null,null,orderBy);
            case PERSON:
                long rowid = ContentUris.parseId(uri);//获取Uri中操作数据的ID
                String where = "personId="+rowid;//获取操作数据库的条件语句
                if (null == whereClause||"".equals(whereClause)){//判断是否还有其他条件语句
                    where += " and "+ whereClause;//组拼条件语句
                }
                return db.query("person",columns, where,whereArgs,null,null,orderBy);
            default:
                throw new IllegalArgumentException("this id Unknown Uri "+uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        /**
         * 用于返回要操作数据MIME类型（类容类型）
         *   1、如果操作的类型属于集合类型数据，那么MIME类型字符串应该以‘vud.android.cursor.dir/’开头
         *      例如：如果要得到所有person记录的Uri为：content://com.example.administrator.foundationdemo.provider.PersonProvider/person
         *            那么要返回的MIME类型字符串应该为：vud.android.cursor.dir/person
         *   2、如果操作的类型属于非集合类型数据，那么MIME类型字符串应该以‘vud.android.cursor.item/’开头
         *      例如：如果要得到id为1的person记录的Uri为：content://com.example.administrator.foundationdemo.provider.PersonProvider/person/1
         *            那么要返回的MIME类型字符串应该为：vud.android.cursor.item/person
         */
        switch (URI_MATCHER.match(uri)){
            case PERSONS:
                return "vud.android.cursor.dir/person";
            case PERSON:
                return "vud.android.cursor.item/person";
            default:
                throw new IllegalArgumentException("this id Unknown Uri "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        //供外部应用向ContentProvider内插入数据
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        //操作ContentProvider数据前，先判断Uri是否匹配，判断scheme与路径是否是要操作的表的路径
        switch (URI_MATCHER.match(uri)){
            case PERSONS:
                long rowid = db.insert("person","nsme",contentValues);//主键值
                //返回修改数据的Uri
                //方法一：手动书写（易错）
                Uri insertUri = Uri.parse("content://com.example.administrator.foundationdemo.provider.PersonProvider/person/"+rowid);
                //方法二：Android提供API（推荐）
                insertUri = ContentUris.withAppendedId(uri,rowid);
                return insertUri;
            default:
                throw new IllegalArgumentException("this id Unknown Uri "+uri);
        }
    }

    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {
        //供外部应用删除ContentProvider内的数据
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        //操作记录数
        int number = 0;
        //操作ContentProvider数据前，先判断Uri是否匹配，判断scheme与路径是否是要操作的表的路径
        switch (URI_MATCHER.match(uri)){
            case PERSONS:
                number = db.delete("person",whereClause,whereArgs);
                break;
            case PERSON:
                long rowid = ContentUris.parseId(uri);//获取Uri中操作数据的ID
                String where = "personId="+rowid;//获取操作数据库的条件语句
                if (null == whereClause||"".equals(whereClause)){//判断是否还有其他条件语句
                    where += " and "+ whereClause;//组拼条件语句
                }
                number = db.delete("person",where,whereArgs);
                break;
            default:
                throw new IllegalArgumentException("this id Unknown Uri "+uri);
        }
        //返回操作记录数
        return number;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String whereClause, String[] whereArgs) {
        //供外部应用更新ContentProvider内的数据
        SQLiteDatabase db = mySQLite.getWritableDatabase();
        //操作记录数
        int number = 0;
        //操作ContentProvider数据前，先判断Uri是否匹配，判断scheme与路径是否是要操作的表的路径
        switch (URI_MATCHER.match(uri)){
            case PERSONS:
                number = db.update("person", contentValues, whereClause,whereArgs);
                break;
            case PERSON:
                long rowid = ContentUris.parseId(uri);//获取Uri中操作数据的ID
                String where = "personId="+rowid;//获取操作数据库的条件语句
                if (null == whereClause||"".equals(whereClause)){//判断是否还有其他条件语句
                    where += " and "+ whereClause;//组拼条件语句
                }
                number = db.update("person",contentValues,where,whereArgs);
                break;
            default:
                throw new IllegalArgumentException("this id Unknown Uri "+uri);
        }
        //返回操作记录数
        return number;
    }

}
