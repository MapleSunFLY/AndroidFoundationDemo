package com.example.administrator.foundationdemo.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.brothers.fly.aichebao.R;
import com.example.administrator.foundationdemo.http.service.ImageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpImageActivity extends AppCompatActivity {

    private EditText http_image_edittext;
    private Button http_image_button;
    private ImageView http_image_imageview;
    private Button http_image_keep;
    private Button http_image_fetch;

    private String urlString = "http://upload-images.jianshu.io/upload_images/4047773-f6c7549ab7c95bd2?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_image);
        init();
    }

    private void init(){

        http_image_edittext = (EditText) findViewById(R.id.http_image_edittext);
        http_image_imageview = (ImageView) findViewById(R.id.http_image_imageview);
        http_image_button = (Button) findViewById(R.id.http_image_button);
        http_image_keep = (Button) findViewById(R.id.http_image_keep);
        http_image_fetch = (Button) findViewById(R.id.http_image_fetch);
        http_image_edittext.setText(urlString);

        http_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImage();
            }
        });
        http_image_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keepImg();
            }
        });

        http_image_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImg();

            }
        });
    }

    private void fetchImg() {
        /**
         * Environment.getExternalStorageState()获取路径是否成功
         */

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path=Environment.getExternalStorageDirectory()+"/img.png";
            File mFile=new File(path);
            //若该文件存在
            if (mFile.exists()) {
                Bitmap bitmap=BitmapFactory.decodeFile(path);
                http_image_imageview.setImageBitmap(bitmap);
            }else {
                Log.d("FLY","图片不存在");
            }
        }

    }

    private void keepImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] date = new byte[0];
                    try {
                        date = ImageService.getImage(urlString);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("FLY", "获取图片异常\n" + e.toString());
                    }
                    File file = new File(Environment.getExternalStorageDirectory(),"img.png");
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(date);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("FLY", "储存图片异常\n" + e.toString());
                }
                Log.d("FLY", "请求成功");

            }
        }).start();
    }

    //开启子线程，进行耗时操作
    private void setImage(){
        Log.d("FLY","请求失败1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = http_image_edittext.getText().toString();
                byte[] date = new byte[0];
                try {
                    date = ImageService.getImage(urlString);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("FLY", "获取图片异常\n" + e.toString());
                    Toast.makeText(HttpImageActivity.this,"获取图片失败",Toast.LENGTH_SHORT).show();
                }
                Log.d("FLY","请求失败1");
                Bitmap bitmap = BitmapFactory.decodeByteArray(date,0,date.length);
                Message msg = new Message();
                msg.obj = bitmap;
                hadler.sendMessage(msg);
            }
        }).start();


    }

    Handler hadler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("FLY", "请求失败2");
            Bitmap bitmap = (Bitmap) msg.obj;
            http_image_imageview.setImageBitmap(bitmap);
        }
    };

}
