package com.example.administrator.foundationdemo.xmlparsepull.service;

import android.util.Xml;

import com.example.administrator.foundationdemo.xmlparsepull.domain.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
public class PersonService {

    public static List<Person> getPersonXML (InputStream xml) throws Exception{
        List<Person> persons = null;
        Person person = null;
        //可通过pull解析器工场得到实例方法==》 XmlPullParserFactory.newInstance().newPullParser();
        XmlPullParser pullParser = Xml.newPullParser();//通Xml过帮助类获得pull解析器实例
        pullParser.setInput(xml,"UTF-8");//为pull解析器设置要解析的XML数据
        /**分析：
         * pull解析器会将数据读到一个 char[] = {-<..XML..>-}中
         * pull解析器会自动读取开始段（及第一段“<?xml version="1.0" encoding="utf-8"?>”）类容，
         * 从而判断是否符合XML开始类容的某一个语法，并触发对应语法所对应的事件
         */
        int exent = pullParser.getEventType();//第一个事件及开始的事件
        while (exent != XmlPullParser.END_DOCUMENT){
            switch (exent){
                case XmlPullParser.START_DOCUMENT://DOCUMENT(开始事件)
                    persons = new ArrayList<Person>();
                    break;
                case XmlPullParser.START_TAG: //节点开始的TAG
                    if ("Person".equals(pullParser.getName())){
                        int id = Integer.parseInt(pullParser.getAttributeValue(0));
                        person = new Person();
                        person.setId(id);
                    }
                    if ("name".equals(pullParser.getName())){
                       String name = pullParser.nextText();
                        person.setName(name);
                    }
                    if ("age".equals(pullParser.getName())){
                        int age = Integer.parseInt(pullParser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG: //节点结束的TAG
                    if ("Person".equals(pullParser.getName())){
                        persons.add(person);
                        person = null;
                    }
                    break;

            }
            //当解析完开始事件之后，PLL解析器不会主动向下解析，
            // 需要调用如下（.next()）API,调用一次向下解析一步
            exent = pullParser.next();
        }


        return persons;
    }
}
