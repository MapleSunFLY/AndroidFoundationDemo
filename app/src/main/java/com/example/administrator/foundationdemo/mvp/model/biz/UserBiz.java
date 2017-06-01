package com.example.administrator.foundationdemo.mvp.model.biz;

import com.example.administrator.foundationdemo.mvp.model.bean.User;

/**
 * <pre>
 *          .----.
 *       _.'__    `.
 *   .--(Q)(OK)---/$\
 * .' @          /$$$\
 * :         ,   $$$$$
 *  `-..__.-' _.-\$$$/
 *        `;_:    `"'
 *      .'"""""`.
 *     /,  FLY  ,\
 *    //         \\
 *    `-._______.-'
 *    ___`. | .'___
 *   (______|______)
 *
 * </pre>
 * Created by FLY on 2017/5/31.
 */
public class UserBiz implements BaseUserBiz{
    @Override
    public void login(final String userName, final String password, final OnLoginListener loginListener) {
        //模拟耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("FLY".equals(userName)&& "123".equals(password)){
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                }else {
                    loginListener.loginFailed();
                }
            }
        }).start();
    }
}
