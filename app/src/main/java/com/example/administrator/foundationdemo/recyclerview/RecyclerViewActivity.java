package com.example.administrator.foundationdemo.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.brothers.fly.aichebao.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<Data> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initData();
        initRecyclerView();
    }

    private void initData() {
        mData = new ArrayList<Data>();
        mData.add(new Data(1,"枫阳","recycler_my","FLY346422332","recycler_two"));
        mData.add(new Data(4,"相册","recycler_photo"));
        mData.add(new Data(5,"收藏","recycler_collect"));
        mData.add(new Data(4, "钱包", "recycler_money"));
        mData.add(new Data(5, "卡包", "recycler_card"));
        mData.add(new Data(4, "表情", "recycler_expression"));
        mData.add(new Data(4, "设置", "recycler_install"));
    }

    private void initRecyclerView() {
        //1 实例化RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //2 为RecyclerView创建布局管理器，这里使用的是LinearLayoutManager，表示里面的Item排列是线性排列
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        handlerRecyclerView();
        //3 设置数据适配器
        mRecyclerView.setAdapter(mAdapter);
    }

    private void handlerRecyclerView() {
        mAdapter = new MyAdapter(this, mData) {
            @Override
            public void convert(MyViewHolder holder, Data data) {
                switch (data.getViewType()){
                    case ViewItems.TEMPLATE_2:
                        handlerTemplate2(holder,data);
                        break;
                    case ViewItems.TEMPLATE_5:
                        handlerTemplate5(holder,data);
                        break;
                    case ViewItems.TEMPLATE_6:
                        handlerTemplate6(holder,data);
                        break;
                    default:
                        Log.d("FLY","错误了");
                        break;
                }
            }
        };

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Data data = (Data) o;
                Toast.makeText(RecyclerViewActivity.this, data.getName() + "：  " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handlerTemplate2(MyViewHolder holder, Data data){
        ImageView photo = holder.getView(R.id.recyclerview_item_my_photo);
        photo.setImageResource(holder.getDrawableId(data.getLogo()));
        TextView name = holder.getView(R.id.recyclerview_item_my_name);
        name.setText(data.getName());
        TextView number = holder.getView(R.id.recyclerview_item_my_number);
        number.setText("微信号: " + data.getNotificationText());
        ImageView two = holder.getView(R.id.recyclerview_item_my_two);
        two.setImageResource(holder.getDrawableId(data.getNotificationImg()));
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecyclerViewActivity.this,"two 000000 ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void handlerTemplate5(MyViewHolder holder, Data data){
        ImageView photo = holder.getView(R.id.recyclerview_item_ordinary_1_img);
        photo.setImageResource(holder.getDrawableId(data.getLogo()));
        TextView name = holder.getView(R.id.recyclerview_item_ordinary_1_text);
        name.setText(data.getName());
        if (null!= data.getNotificationText() ){
            TextView number = holder.getView(R.id.recyclerview_item_notification_1_img);
            number.setVisibility(View.VISIBLE);
            number.setText(data.getNotificationText());
        }
       if (null != data.getNotificationImg()){
           ImageView two = holder.getView(R.id.recyclerview_item_notification_1_text);
           two.setVisibility(View.VISIBLE);
           two.setImageResource(holder.getDrawableId(data.getNotificationImg()));
       }

    }
    private void handlerTemplate6(MyViewHolder holder, Data data){
        ImageView photo = holder.getView(R.id.recyclerview_item_ordinary_2_img);
        photo.setImageResource(holder.getDrawableId(data.getLogo()));
        TextView name = holder.getView(R.id.recyclerview_item_ordinary_2_text);
        name.setText(data.getName());
        if (null!= data.getNotificationText() ){
            TextView number = holder.getView(R.id.recyclerview_item_notification_2_img);
            number.setVisibility(View.VISIBLE);
            number.setText(data.getNotificationText());
        }
        if (null != data.getNotificationImg()){
            ImageView two = holder.getView(R.id.recyclerview_item_notification_2_text);
            two.setVisibility(View.VISIBLE);
            two.setImageResource(holder.getDrawableId(data.getNotificationImg()));
        }
    }


}
