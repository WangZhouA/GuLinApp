package saiyi.com.gulin_new_wz.utils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 滕涛 on 2017/8/7.
 */

public final class LogUtils {
    /**
     * Master switch.To catch error info you need set this value below Log.WARN
     */
    public static final int DEBUG_LEVEL = 0;
    /**
     * 'System.out' switch.When it is true, you can see the 'System.out' log.
     * <p>
     * Otherwise, you cannot.
     */
    public static final boolean DEBUG_SYSOUT = false;

    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param obj
     */

    public static void v(Object obj) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.v(tag, msg);
        }
    }


    /**
     * Send a {@link #DEBUG_LEVEL} log message.
     *
     * @param obj
     */

    public static void d(Object obj) {
        if (Log.DEBUG > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.d(tag, msg);
        }
    }


    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param obj
     */

    public static void i(Object obj) {
        if (Log.INFO > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.i(tag, msg);
        }
    }


    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param obj
     */

    public static void w(Object obj) {
        if (Log.WARN > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.w(tag, msg);
        }
    }


    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param obj
     */

    public static void e(Object obj) {
        if (Log.ERROR > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.e(tag, msg);
        }
    }


    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            <p>
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */

    public static void v(String tag, String msg) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.v(tag, msg);
        }
    }


    /**
     * Send a {@link #DEBUG_LEVEL} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            <p>
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */

    public static void d(String tag, String msg) {
        if (Log.DEBUG > DEBUG_LEVEL) {
            Log.d(tag, msg);
        }
    }


    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */

    public static void i(String tag, String msg) {
        if (Log.INFO > DEBUG_LEVEL) {
            Log.i(tag, msg);
        }
    }


    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */

    public static void w(String tag, String msg) {
        if (Log.WARN > DEBUG_LEVEL) {
            Log.w(tag, msg);
        }
    }


    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (Log.ERROR > DEBUG_LEVEL) {
            Log.e(tag, msg);
        }
    }


    private static String getCallHierarchy() {
        String result = "";
        StackTraceElement[] trace = (new Exception()).getStackTrace();
        for (int i = 2; i < trace.length; i++) {
            result += "\r\t" + trace[i].getClassName() + ""
                    + trace[i].getMethodName() + "():"
                    + trace[i].getLineNumber();
        }
        return result;
    }

    private static String getClassName() {
        String result = "";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        return result;
    }


    /**
     * Realization of double click jump events.
     *
     * @return
     */

    private static String callMethodAndLine() {
        String result = "at ";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result += thisMethodStack.getClassName() + "";
        result += thisMethodStack.getMethodName();
        result += "(" + thisMethodStack.getFileName();
        result += ":" + thisMethodStack.getLineNumber() + ")  ";
        return result;
    }


    // 写txt文件，追加写
    public static void writeFileSdcard(String message) {
        message = getCurrentTime() + message + "\n";
        try {
            FileOutputStream fout = new FileOutputStream(getLogPath(), true);
            byte[] bytes = message.getBytes("UTF-8");
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 获取系统当前时间，格式为2015-10-12-13:20:33

    @SuppressLint("SimpleDateFormat")

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }


    private static String getLogPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/log.txt";
    }
}
