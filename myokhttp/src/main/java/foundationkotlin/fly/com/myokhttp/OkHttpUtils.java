package foundationkotlin.fly.com.myokhttp;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by FLY on 2017/5/24.
 */
public class OkHttpUtils {

    private static OkHttpUtils okHttpUtils;
    private OkHttpClient mOkHttpClient;

    private OkHttpUtils(OkHttpClient okHttpClient){
        if (okHttpClient == null){
            //创建okHttpClient对象
            mOkHttpClient = new OkHttpClient();
        }else {
            mOkHttpClient = okHttpClient;
        }


    }

    //初始化单利
    public static OkHttpUtils initClient(OkHttpClient okHttpClient){
        if (okHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils == null){
                    okHttpUtils = new OkHttpUtils(okHttpClient);
                }
            }
        }

        return okHttpUtils;
    }

    //获取类实例
    private static OkHttpUtils getInstance(){
        return initClient(null);
    }






}
