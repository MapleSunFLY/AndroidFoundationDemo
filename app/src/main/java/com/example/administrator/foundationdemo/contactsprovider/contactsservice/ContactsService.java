package com.example.administrator.foundationdemo.contactsprovider.contactsservice;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;

import com.example.administrator.foundationdemo.contactsprovider.domain.ContactText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
public class ContactsService {
    private Context context;
    public ContactsService(Context context){
        this.context = context;
    }

    //获取所有联系人
    public List<ContactText> visitContacts(){
        List<ContactText> list = new ArrayList<ContactText>();
        Uri uri = Uri.parse("content://com.android.contacts/contacts");//所有联系人的工作路径
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);//获取所有联系人的ID
        while (cursor.moveToNext()){
            ContactText conract = new ContactText();
            int contactcId = cursor.getInt(0);
            uri = Uri.parse("content://com.android.contacts/contacts/" + contactcId + "/Data");//某一个联系人工作路径
            Cursor dateCursor = resolver.query(uri, new String[]{"mimetype","data1","data2"}, null, null, null);//获取某一个联系人的信息
            while (dateCursor.moveToNext()){
                String date = dateCursor.getString(dateCursor.getColumnIndex("data1"));//信息
                String type = dateCursor.getString(dateCursor.getColumnIndex("mimetype"));//内容类型
                if("vnd.android.cursor.item/name".equals(type)){//name
                    conract.setName(date);
                }
                if("vnd.android.cursor.item/email_v2".equals(type)){//name
                    conract.setEmail(date);
                }
                if("vnd.android.cursor.item/phone_v2".equals(type)){//name
                    conract.setPhone(date);
                }
            }
            dateCursor.close();
            list.add(conract);
            Log.d("FLY","contactcId"+contactcId);
        }
        cursor.close();
        return list;
    }
    //根据号码获取联系人的姓名
    public String visitContactNameByPhoneNumber(String phoneNumber){
        String name = null;
        Uri uri = Uri.parse("content://com.android.contacts/Data/phones/filter/"+phoneNumber);//电话查询的工作路径
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri,new String[]{"display_name"},null,null,null);
        if (cursor.moveToFirst()){
           name = cursor.getString(cursor.getColumnIndex("display_name"));
        }else {
            name = "未知联系人";
        }
        cursor.close();
        return name;
    }

    //添加联系人到通讯录
    public void addContact(ContactText conract){
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        long contacrId = ContentUris.parseId(resolver.insert(uri,values));
        //信息Uri
        uri = Uri.parse("content://com.android.contacts/Data");
        //添加姓名
        values.put("raw_contact_id",contacrId);
        values.put("mimetype","vnd.android.cursor.item/name");
        values.put("data2", conract.getName());
        resolver.insert(uri, values);

        //添加电话
        values.put("raw_contact_id",contacrId);
        values.put("mimetype","vnd.android.cursor.item/phone_v2");
        values.put("data2", "2");
        values.put("data1",conract.getPhone());
        resolver.insert(uri,values);

        //添加邮箱
        values.put("raw_contact_id",contacrId);
        values.put("mimetype","vnd.android.cursor.item/Email_v2");
        values.put("data2", "2");
        values.put("data1",conract.getEmail());
        resolver.insert(uri,values);
    }

    public void addContact2(ContactText conract) throws RemoteException, OperationApplicationException {

        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> operations= new ArrayList<ContentProviderOperation>();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentProviderOperation operation_1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build();
        operations.add(operation_1);

        uri = Uri.parse("content://com.android.contacts/Data");
        ContentProviderOperation operation_2 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id",0)//0为list中第一个对象，意思是使用第一个对象操作返回记录ID为这个字段的
                .withValue("mimetype","vnd.android.cursor.item/name")
                .withValue("data2", conract.getName())
                .build();
        operations.add(operation_2);

        ContentProviderOperation operation_3 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)//0为list中第一个对象，意思是使用第一个对象操作返回记录ID为这个字段的
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", conract.getPhone())
                .withValue("data2","2" )
                .build();
        operations.add(operation_3);

        ContentProviderOperation operation_4 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id",0)//0为list中第一个对象，意思是使用第一个对象操作返回记录ID为这个字段的
                .withValue("mimetype","vnd.android.cursor.item/Email_v2")
                .withValue("data1", conract.getEmail())
                .withValue("data2","2" )
                .build();
        operations.add(operation_4);

        resolver.applyBatch("com.android.contacts",operations);
    }



}
