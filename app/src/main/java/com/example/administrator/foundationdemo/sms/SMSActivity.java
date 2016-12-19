package com.example.administrator.foundationdemo.sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.foundationdemo.R;

import java.util.ArrayList;

public class SMSActivity extends AppCompatActivity {

    private EditText sms_phone_number_edittext;
    private EditText sms_text_edittext;
    private Button sms_button;


    PendingIntent sendIntent;
    PendingIntent backIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(this);
        smsSending();
    }


    private void init(Context context){

        setContentView(R.layout.activity_sms);

        sms_phone_number_edittext = (EditText) findViewById(R.id.sms_phone_number_edittext);
        sms_text_edittext = (EditText) findViewById(R.id.sms_text_edittext);
        sms_button = (Button) findViewById(R.id.sms_button);

        //处理返回的发送状态
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        sendIntent= PendingIntent.getBroadcast(context, 0, sentIntent,
                0);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(SMSActivity.this,
                                "短信发送成功", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(SMSActivity.this,
                                "短信发送失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(SMSActivity.this,
                                "短信发送失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(SMSActivity.this,
                                "短信发送失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));

        //处理返回的接收状态
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        // create the deilverIntent parameter
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        backIntent= PendingIntent.getBroadcast(context, 0,
                deliverIntent, 0);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                Toast.makeText(SMSActivity.this,
                        "收信人已经成功接收", Toast.LENGTH_SHORT)
                        .show();
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));
    }

    private void smsSending(){
        sms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smsPhonenumber = sms_phone_number_edittext.getText().toString();
                String smsText = sms_text_edittext.getText().toString();

                if ("".equals(smsPhonenumber)||"".equals(smsText)){
                    Toast.makeText(SMSActivity.this,"电话号码或短信内容不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                SmsManager smsManager = SmsManager.getDefault();//获取短信管理器的实例
                ArrayList<String> smsTextsArrayList = smsManager.divideMessage(smsText);//当短信类容超过一条短信类容时，进行拆分
                for (String text:smsTextsArrayList){
                    /**sendTextMessage (String destinationAddress, String scAddress, String text, PendingIntent sentIntent, PendingIntent deliveryIntent)
                     destinationAddress      发送短信的地址（也就是号码）
                     scAddress               短信服务中心，如果为null，就是用当前默认的短信服务中心
                     text                    短信内容
                     sentIntent              如果不为null，当短信发送成功或者失败时，这个PendingIntent会被广播出去成功的结果代码是Activity.RESULT_OK，
                                             或者下面这些错误之一  ：RESULT_ERROR_GENERIC_FAILURE,RESULT_ERROR_RADIO_OFF,RESULT_ERROR_NULL_PDU等
                     通俗点说： 发送 -->中国移动 --> 中国移动发送失败 --> 返回发送成功或失败信号 --> 后续处理   即，这个意图包装了短信发送状态的信息
                     deliveryIntent          如果不为null，当这个短信发送到接收者那里，这个PendtingIntent会被广播，
                                             状态报告生成的pdu（指对等层次之间传递的数据单位）会拓展到数据（"pdu"）
                     通俗点就是：发送 -->中国电信 --> 中国电信发送成功 --> 返回对方是否收到这个信息 --> 后续处理  即：这个意图包装了短信是否被对方收到的状态信息（供应商已经发送成功，但是对方没有收到）。
                     */
                    smsManager.sendTextMessage(smsPhonenumber,null,text,sendIntent,backIntent);
                }
            }
        });
    }



}
