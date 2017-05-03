package com.example.administrator.foundationdemo.file;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brothers.fly.aichebao.R;


public class FileActivity extends AppCompatActivity {

    private EditText file_name_edittext;
    private EditText file_text_edittext;
    private Button file_writing_button;
    private Button file_read_button;
    private TextView file_read_text;

    FileService fileService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        file_name_edittext = (EditText) findViewById(R.id.file_name_edittext);
        file_text_edittext = (EditText) findViewById(R.id.file_text_edittext);
        file_writing_button = (Button) findViewById(R.id.file_writing_button);
        file_read_button = (Button) findViewById(R.id.file_read_button);
        file_read_text = (TextView) findViewById(R.id.file_read_text);

        fileService = new FileService(FileActivity.this);

        //数据保存按钮
        file_writing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = file_name_edittext.getText().toString();
                String fileText = file_text_edittext.getText().toString();


                try {
                    //判断SDCard是否存在并且可写
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        fileService.saveToSDCard(fileName,fileText);
                        Toast.makeText(FileActivity.this,"保存成功",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(FileActivity.this,"SDCard不存在或不可写",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(FileActivity.this,"保存失败",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        //数据读取按钮
        file_read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = file_name_edittext.getText().toString();
                try {
                    file_read_text.setText(fileService.read(fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
