package com.example.administrator.foundationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.foundationdemo.file.FileActivity;
import com.example.administrator.foundationdemo.phonecall.PhoneCallActivity;
import com.example.administrator.foundationdemo.provider.ProviderActivity;
import com.example.administrator.foundationdemo.sharedpreference.SharedPreferencesActivity;
import com.example.administrator.foundationdemo.sms.SMSActivity;
import com.example.administrator.foundationdemo.sqlite.SQLiteActivity;
import com.example.administrator.foundationdemo.xmlparsepull.XMLParsePullActivity;

public class MainActivity extends AppCompatActivity {

    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent();
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_call:
                mIntent.setClass(MainActivity.this,PhoneCallActivity.class);
                break;
            case R.id.sms:
                mIntent.setClass(MainActivity.this,SMSActivity.class);
                break;
            case R.id.file:
                mIntent.setClass(MainActivity.this,FileActivity.class);
                break;
            case R.id.xml_parse:
                mIntent.setClass(MainActivity.this, XMLParsePullActivity.class);
                break;
            case R.id.shard_perferences:
                mIntent.setClass(MainActivity.this, SharedPreferencesActivity.class);
                break;
            case R.id.sqlite:
                mIntent.setClass(MainActivity.this, SQLiteActivity.class);
                break;
            case R.id.person_provider:
                mIntent.setClass(MainActivity.this, ProviderActivity.class);
                break;
            default:
                Toast.makeText(this,"What did you do",Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(mIntent);
    }
}