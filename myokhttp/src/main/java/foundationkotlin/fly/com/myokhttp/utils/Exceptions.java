package foundationkotlin.fly.com.myokhttp.utils;

/**
 * Created by FLY on 2017/5/26.
 */
public class Exceptions {

    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
