package com.example.administrator.foundationdemo.toolbarmenutext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.brothers.fly.aichebao.R;


public class ToolbarMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_menu);
        initToolbar();
    }


    private void initToolbar(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar_main);
        //添加溢出菜单
        mToolbar.inflateMenu(R.menu.setting_menu);
        // 添加菜单点击事件
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_search:
                        showToast(R.string.main_menu_search);
                        break;
                    case R.id.item_chat:
                        //点击设置菜单
                        showToast(R.string.main_menu_chat);
                        break;
                    case R.id.item_add_person:
                        //点击设置菜单
                        showToast(R.string.main_menu_add_person);
                        break;
                    case R.id.item_richscan:
                        //点击设置菜单
                        showToast(R.string.main_menu_richscan);
                        break;
                    case R.id.item_money:
                        //点击设置菜单
                        showToast(R.string.main_menu_money);
                        break;
                    case R.id.item_help:
                        //点击设置菜单
                        showToast(R.string.main_menu_help);
                        break;
                }
                return false;
            }
        });

    }

    private void showToast(int id){
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
    }
}
