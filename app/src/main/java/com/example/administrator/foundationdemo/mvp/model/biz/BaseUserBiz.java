package com.example.administrator.foundationdemo.mvp.model.biz;

/**
 * <pre>
 *          .----.
 *       _.'__    `.
 *   .--(@)(@@)---/$\
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
public interface BaseUserBiz {
     void login(String userName,String password,OnLoginListener loginListener);
}
