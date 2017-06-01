package com.example.administrator.foundationdemo.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brothers.fly.aichebao.R;
import com.example.administrator.foundationdemo.mvp.model.bean.User;
import com.example.administrator.foundationdemo.mvp.presenter.UserLoginPresenter;
import com.example.administrator.foundationdemo.mvp.view.IUserLoginView;

public class LoginActivity extends AppCompatActivity implements IUserLoginView{

   private EditText login_user_name;
    private EditText login_passwoed;
    private Button login;
    private Button clear;

    private ProgressBar progress;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_login);
        login_user_name = (EditText) findViewById(R.id.login_user_name);
        login_passwoed = (EditText) findViewById(R.id.login_passwoed);
        login = (Button) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.clear);
        progress = (ProgressBar) findViewById(R.id.progress);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });

    }

    @Override
    public String getUserName() {
        return login_user_name.getText().toString();
    }

    @Override
    public String getPassword() {
        return login_passwoed.getText().toString();
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void clearUserName() {
        login_user_name.setText("");
    }

    @Override
    public void clearPassword() {
        login_passwoed.setText("");
    }

    @Override
    public void startActivity(User user) {
        Toast.makeText(this,"login to main",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"login to error",Toast.LENGTH_SHORT).show();
    }
}
