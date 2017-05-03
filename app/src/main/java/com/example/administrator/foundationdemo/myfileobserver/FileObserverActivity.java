package com.example.administrator.foundationdemo.myfileobserver;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.brothers.fly.aichebao.R;


public class FileObserverActivity extends AppCompatActivity {

    MyFileObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_observer);



        findViewById(R.id.fileobserver_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = Environment.getExternalStorageDirectory().toString();
                Log.d("FLY","SD: "+filename);
                mObserver = new MyFileObserver(filename);

                mObserver.startWatching();
            }
        });

        findViewById(R.id.fileobserver_off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mObserver != null){
                    mObserver.stopWatching();
                }

            }
        });
    }
}
