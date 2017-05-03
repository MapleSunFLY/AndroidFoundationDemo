package com.example.administrator.foundationdemo.phonecall;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brothers.fly.aichebao.R;


public class PhoneCallActivity extends AppCompatActivity {

    private EditText phone_call_edittext;
    private Button phone_call_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        phone_call_edittext = (EditText) findViewById(R.id.phone_call_edittext);
        phone_call_button = (Button) findViewById(R.id.phone_call_button);

        phone_call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String phoneNumber = phone_call_edittext.getText().toString();

                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.CALL");//需要权限：android.permission.CALL_PHONE
//                    intent.addCategory("android.intent.category.DEFAULT");所以这一行可以省略
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);//方法内部会自动为Intent添加类别：android.intent.category.DEFAULT
                }catch (Exception e){
                    Toast.makeText(PhoneCallActivity.this,"输入有误",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
