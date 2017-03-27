package com.example.administrator.foundationdemo.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.administrator.foundationdemo.R;
import com.example.administrator.foundationdemo.http.service.ImageService;

import java.io.IOException;

public class HttpImageActivity extends AppCompatActivity {

    private EditText http_image_edittext;
    private Button http_image_button;
    private ImageView http_image_imageview;

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
        http_image_edittext.setText(urlString);

        http_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImage();
            }
        });
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
