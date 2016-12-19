package com.example.administrator.foundationdemo.xmlparsepull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.foundationdemo.R;
import com.example.administrator.foundationdemo.xmlparsepull.domain.Person;
import com.example.administrator.foundationdemo.xmlparsepull.service.PersonService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class XMLParsePullActivity extends AppCompatActivity {

    TextView xml_parse_text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        xmlParse();
    }

    private void init(){
        setContentView(R.layout.activity_xmlparse_pall);
        xml_parse_text = (TextView) findViewById(R.id.xml_parse_text);
    }

    private void xmlParse(){

        try {
            InputStream xml = this.getResources().getAssets().open("person.xml");// 获取assets下XM文件输出流
           List<Person> persons = PersonService.getPersonXML(xml);
            for (Person person : persons ){
                String text = xml_parse_text.getText()+" \n id："+person.getId()+"\n name："+person.getName()+"\nage："+person.getAge();
                Log.d("FLY",text);
                xml_parse_text.setText(text);
            }
        } catch (Exception e) {
            Log.d("FLY","出错了"+e);
            e.printStackTrace();
        }
    }
}
