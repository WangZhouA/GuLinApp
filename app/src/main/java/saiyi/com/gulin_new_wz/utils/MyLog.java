package saiyi.com.gulin_new_wz.utils;

import android.util.Log;

/**
 * 自定义Log,上线时,不打印
 */
public class MyLog {
    static String TGA = "saiyi";

    public static void e(String data) {
        if (true) {
            Log.e(TGA, data);
        }
    }

    public static void d(String data) {
        if (true) {
            Log.d(TGA, data);
        }
    }


}
