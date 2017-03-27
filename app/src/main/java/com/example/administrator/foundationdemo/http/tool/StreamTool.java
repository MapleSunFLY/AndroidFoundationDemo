package com.example.administrator.foundationdemo.http.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/1/3.
 */
public class StreamTool {
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int bytrsLen = 0;
        while((bytrsLen =inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,bytrsLen);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }
}
