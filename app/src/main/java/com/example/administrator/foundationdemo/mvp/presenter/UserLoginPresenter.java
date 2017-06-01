package com.example.administrator.foundationdemo.mvp.presenter;

import android.os.Handler;

import com.example.administrator.foundationdemo.mvp.model.bean.User;
import com.example.administrator.foundationdemo.mvp.model.biz.BaseUserBiz;
import com.example.administrator.foundationdemo.mvp.model.biz.OnLoginListener;
import com.example.administrator.foundationdemo.mvp.model.biz.UserBiz;
import com.example.administrator.foundationdemo.mvp.view.IUserLoginView;

import java.util.List;

/**
 * <pre>
 *           .----.
 *        _.'__    `.
 *    .--(Q)(OK)---/$\
 *  .' @          /$$$\
 *  :         ,   $$$$$
 *   `-..__.-' _.-\$$$/
 *         `;_:    `"'
 *       .'"""""`.
 *      /,  FLY  ,\
 *     //         \\
 *     `-._______.-'
 *     ___`. | .'___
 *    (______|______)
 *
 * </pre>
 * Created by FLY on 2017/5/31.
 */
public class UserLoginPresenter {
    private BaseUserBiz baseUserBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView){
        this.userLoginView = userLoginView;
        this.baseUserBiz = new UserBiz();
    }

    public void login(){
        userLoginView.showLoading();

        baseUserBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.startActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
