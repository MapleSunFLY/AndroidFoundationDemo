package com.example.administrator.foundationdemo.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.foundationdemo.R;
import com.example.administrator.foundationdemo.sqlite.domain.Person;
import com.example.administrator.foundationdemo.sqlite.service.MySQLite;
import com.example.administrator.foundationdemo.sqlite.service.PersonSQLite;

import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    private EditText person_name_edittext;
    private EditText person_phone_edittext;
    private EditText person_id_edittext;
    private EditText person_num1_edittext;
    private EditText person_num2_edittext;
    private TextView text;
    private PersonSQLite personSQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        init();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.save_sql_button:
                personSQLite.save(new Person(person_name_edittext.getText().toString(),person_phone_edittext.getText().toString()));
                break;
            case R.id.delete_sql_button:
                String personId = person_id_edittext.getText().toString();
                if (null == personId||"".equals(personId)){
                    person_id_edittext.setVisibility(View.VISIBLE);
                    person_id_edittext.setHint("请输入删除personId");
                }else {
                    personSQLite.delete(Integer.parseInt(personId));
                    person_id_edittext.setText("");
                    person_id_edittext.setVisibility(View.GONE);
                }
                break;
            case R.id.update_sql_button:

                break;
            case R.id.find_sql_button:
                String personId1 = person_id_edittext.getText().toString();
                if (null == personId1||"".equals(personId1)){
                    person_id_edittext.setVisibility(View.VISIBLE);
                    person_id_edittext.setHint("请输入查询personId");
                }else {
                    Person person = personSQLite.find(Integer.parseInt(personId1));
                    if (person==null){
                        Toast.makeText(this,"无结果",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    text.setText("personId："+person.getId()+"\nname："+person.getName()+"\nphone"+person.getPhone());
                    person_id_edittext.setText("");
                    person_id_edittext.setVisibility(View.GONE);
                }
                break;
            case R.id.scroll_date_sql_button:
                String num1 = person_num1_edittext.getText().toString();
                String num2 = person_num2_edittext.getText().toString();
                if (null == num1||"".equals(num1)||null == num2||"".equals(num2)){
                    person_num1_edittext.setVisibility(View.VISIBLE);
                    person_num2_edittext.setVisibility(View.VISIBLE);
                }else {
                    List<Person> list = personSQLite.getScrollDate(Integer.parseInt(num1),Integer.parseInt(num2));
                    if (list==null){
                        Toast.makeText(this,"无结果",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (Person person : list){
                        text.setText(text.getText().toString()+"\npersonId："+person.getId()+"\nname："+person.getName()+"\nphone"+person.getPhone());
                    }
                    person_num1_edittext.setText("");
                    person_num2_edittext.setText("");
                    person_num1_edittext.setVisibility(View.GONE);
                    person_num2_edittext.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    private void init(){
        person_name_edittext = (EditText) findViewById(R.id.person_name_edittext);
        person_phone_edittext = (EditText)findViewById(R.id.person_phone_edittext);
        person_id_edittext = (EditText) findViewById(R.id.person_id_edittext);
        person_num1_edittext = (EditText)findViewById(R.id.person_num1_edittext);
        person_num2_edittext = (EditText) findViewById(R.id.person_num2_edittext);
        text = (TextView) findViewById(R.id.text);
        personSQLite = new PersonSQLite(this);
    }

}
