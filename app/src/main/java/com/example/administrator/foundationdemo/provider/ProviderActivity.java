package com.example.administrator.foundationdemo.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.foundationdemo.R;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
    }

    private void testInsert(){
        //别的应用添加类容提供者数据需要代码
        Uri uri = Uri.parse("content://com.example.administrator.foundationdemo.provider.PersonProvider/person");
        ContentResolver contentResolver = this.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name","laowang");
        values.put("phone", "12345678910");
        contentResolver.insert(uri, values);
    }
    private void testDelete(){
        //别的应用添加类容提供者数据需要代码
        Uri uri = Uri.parse("content://com.example.administrator.foundationdemo.provider.PersonProvider/person/1");
        ContentResolver contentResolver = this.getContentResolver();
        contentResolver.delete(uri, null, null);
    }
    private void testUpdate(){
        //别的应用添加类容提供者数据需要代码
        Uri uri = Uri.parse("content://com.example.administrator.foundationdemo.provider.PersonProvider/person/1");
        ContentResolver contentResolver = this.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name","xxxxx");
        contentResolver.update(uri, values, null, null);
    }
    private void testQuery(){
        //别的应用添加类容提供者数据需要代码
        Uri uri = Uri.parse("content://com.example.administrator.foundationdemo.provider.PersonProvider/person");
        ContentResolver contentResolver = this.getContentResolver();
                                 //所有字段所有类容 id升序排列
       Cursor cursor = contentResolver.query(uri, null, null, null, "personId asc");
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Log.d("FLY","name："+name+"\n");
        }
        cursor.close();
    }
}
