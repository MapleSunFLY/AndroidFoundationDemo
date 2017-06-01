package com.example.administrator.foundationdemo.mvp.view;

import com.example.administrator.foundationdemo.mvp.model.bean.User;

/**
 * <pre>
 *            .----.
 *         _.'__    `.
 *     .--(Q)(OK)---/$\
 *   .' @          /$$$\
 *   :         ,   $$$$$
 *    `-..__.-' _.-\$$$/
 *          `;_:    `"'
 *        .'"""""`.
 *       /,  FLY  ,\
 *      //         \\
 *      `-._______.-'
 *      ___`. | .'___
 *     (______|______)
 *
 * </pre>
 * Created by FLY on 2017/5/31.
 */
public interface IUserLoginView {

    String getUserName();
    String getPassword();

    void showLoading();
    void hideLoading();

    void clearUserName();
    void clearPassword();

    void startActivity(User user);
    void showFailedError();


}
