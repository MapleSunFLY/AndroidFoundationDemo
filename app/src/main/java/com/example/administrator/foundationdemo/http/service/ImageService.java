package com.example.administrator.foundationdemo.http.service;

import android.util.Log;
import android.widget.Toast;

import com.example.administrator.foundationdemo.http.tool.StreamTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/29.
 */
public class ImageService {

    public static byte[] getImage(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//得到基于HTTP协议的链接对象
        httpURLConnection.setConnectTimeout(5000);//请求超时时间
        httpURLConnection.setRequestMethod("GET");//请求方式

        if (httpURLConnection.getResponseCode() == 200){
            InputStream inputStream = httpURLConnection.getInputStream();
            return StreamTool.readInputStream(inputStream);
        }else {
            Log.d("FLY","请求失败"+httpURLConnection.getResponseCode());
        }
        return null;
    }

    public static InputStream getImageStream(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//得到基于HTTP协议的链接对象
        httpURLConnection.setConnectTimeout(5000);//请求超时时间
        httpURLConnection.setRequestMethod("GET");//请求方式

        if (httpURLConnection.getResponseCode() == 200){
            InputStream inputStream = httpURLConnection.getInputStream();
            return inputStream;
        }else {
            Log.d("FLY","请求失败"+httpURLConnection.getResponseCode());
        }
        return null;
    }
}
