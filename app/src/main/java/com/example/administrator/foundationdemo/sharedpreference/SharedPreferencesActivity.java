package com.example.administrator.foundationdemo.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.foundationdemo.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText shard_perferences_name_edittext ;
    EditText shard_perferences_age_edittext;
    TextView shard_perferences_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.activity_shared_preferrence);
        shard_perferences_name_edittext = (EditText) findViewById(R.id.shard_perferences_name_edittext);
        shard_perferences_age_edittext = (EditText) findViewById(R.id.shard_perferences_age_edittext);
        shard_perferences_text = (TextView) findViewById(R.id.shard_perferences_text);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.shard_perferences_storage_button:
                storage();
                break;
            case R.id.shard_perferences_read_button:
                read();
                break;
        }
    }

    private static final String SP_INFOR = "SP_INFOR";//SharedPreferences创建的文件名称
    //数据储存
    private void storage(){
        String name = shard_perferences_name_edittext.getText().toString();
        String age = shard_perferences_age_edittext.getText().toString();
        /**获取SharedPreferences实例
         * public SharedPreferences getSharedPreferences (String name, int mode)
         * 其中name为Preferences的文件名
         * mode有以下三种：
         * MODE_PRIVATE 表示当下应用程序专用
         * MODE_WORLD_READABLE 表示数据能被其他应用应用程序读，但是不能写。
         * MODE_WORLD_WRITEABLE 表示数据能被其他应用应用程序读，写。
         */
        SharedPreferences sharedPreferences = this.getSharedPreferences(SP_INFOR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("name",name);//键值对
        editor.putString("age",age);
        editor.commit();//内容提交
        Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
    }

    //数据读取
    private void read(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(SP_INFOR, Context.MODE_PRIVATE);//获取SharedPreferences实例
        String name = sharedPreferences.getString("name", "数据为null");
        String age = sharedPreferences.getString("age","数据为null");
        shard_perferences_text.setText("name："+name+"\nage："+age);
    }

}
