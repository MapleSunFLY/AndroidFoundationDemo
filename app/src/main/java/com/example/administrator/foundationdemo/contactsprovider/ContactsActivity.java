package com.example.administrator.foundationdemo.contactsprovider;

import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.foundationdemo.R;
import com.example.administrator.foundationdemo.contactsprovider.contactsservice.ContactsService;
import com.example.administrator.foundationdemo.contactsprovider.domain.ContactText;

public class ContactsActivity extends AppCompatActivity {

    private EditText contact_name ;
    private EditText contact_phone;
    private EditText contact_email;
    private EditText contact_visit_phone;
    private TextView contact_text;
    private ContactsService contactsService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        init();
    }

    private void init(){
        contact_name = (EditText) findViewById(R.id.contact_name);
        contact_phone = (EditText) findViewById(R.id.contact_phone);
        contact_email = (EditText) findViewById(R.id.contact_email);
        contact_visit_phone = (EditText) findViewById(R.id.contact_visit_phone);
        contact_text = (TextView) findViewById(R.id.contact_text);

        contactsService = new ContactsService(this);
    }


    public void onClick (View view){
        ContactText contactText = new ContactText(contact_name.getText().toString(),contact_phone.getText().toString(),contact_email.getText().toString());
        switch (view.getId()){
            case R.id.add_contact_button1:
                contactsService.addContact(contactText);
                break;
            case R.id.add_contact_button2:
                try {
                    contactsService.addContact2(contactText);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.visit_contact_button:
                contact_text.setText(contactsService.visitContactNameByPhoneNumber(contact_visit_phone.getText().toString()));
                break;
            case R.id.visit_contacts_button:

                for (ContactText contactText1 :contactsService.visitContacts()){
                    Log.d("FLY", "contactcId" + contactText1.getName());
                    contact_text.setText(contact_text.getText().toString()+"\n"+contactText1.getName());
                }
                break;
        }
    }

}
