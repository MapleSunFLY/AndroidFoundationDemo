package com.example.administrator.foundationdemo.provider;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.foundationdemo.R;

public class SourceProviderActivity extends AppCompatActivity {

    //要监听其变化的Uri
    private Uri uri = Uri.parse("content://com.example.administrator.foundationdemo.provider.PersonProvider/person");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_provider);
        //设置监听器
        this.getContentResolver().registerContentObserver(uri,true,new PersonCotentObserver(new Handler()));
    }

    private class PersonCotentObserver extends ContentObserver{

        public PersonCotentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {

            //是否是自己修改数据
            if (!selfChange){
                //select * from person order by personId decs limit 1
                Cursor cursor = getContentResolver().query(uri, null, null, null, "personId decs limit 1");
                if (cursor.moveToFirst()){
                     String name = cursor.getString(cursor.getColumnIndex("name"));
                    Log.d("FLY",name);
                }
            }
        }
    }
}
