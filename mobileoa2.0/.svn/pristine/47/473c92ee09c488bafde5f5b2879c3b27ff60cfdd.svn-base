package com.idxk.mobileoa.utils.common.android;

import android.util.Log;

import com.idxk.mobileoa.android.application.MobileApplication;

public class Logger {

    protected static final String TAG = "basePlatform";
    public static boolean isDebug = true;

    private static void initDebug() {

        isDebug = Boolean.parseBoolean(MobileApplication.getInstance().debug);
//        isDebug = true;
    }

    public static void v(String msg) {
        initDebug();
        if (isDebug)
            Log.v(TAG, buildMessage(msg));
    }

    public static void v(String msg, Throwable thr) {
        initDebug();
        if (isDebug)
            Log.v(TAG, buildMessage(msg), thr);

    }

    public static void d(String msg) {
        initDebug();
        if (isDebug)
            Log.d(TAG, buildMessage(msg));

    }

    public static void d(String msg, Throwable thr) {
        initDebug();
        if (isDebug)
            Log.d(TAG, buildMessage(msg), thr);
    }


    public static void i(String msg) {
        initDebug();
        if (isDebug)
            Log.i(TAG, buildMessage(msg));
    }

    public static void i(String msg, Throwable thr) {
        initDebug();
        if (isDebug)
            Log.i(TAG, buildMessage(msg), thr);
    }

    public static void e(String msg) {
        initDebug();
        if (isDebug)
            Log.e(TAG, buildMessage(msg));
    }

    public static void ee(String msg) {
        initDebug();

        Log.e(TAG, buildMessage(msg));
    }


    public static void w(String msg) {
        initDebug();
        Log.w(TAG, buildMessage(msg));
    }


    public static void w(String msg, Throwable thr) {
        initDebug();
        Log.w(TAG, buildMessage(msg), thr);
    }


    public static void w(Throwable thr) {
        initDebug();
        Log.w(TAG, buildMessage(""), thr);
    }


    public static void e(String msg, Throwable thr) {
        initDebug();
        if (isDebug)
            Log.e(TAG, buildMessage(msg), thr);
    }


    protected static String buildMessage(String msg) {
        initDebug();
        StackTraceElement caller = new Throwable().fillInStackTrace()
                .getStackTrace()[2];
//        String mess = new StringBuilder().append(caller.getClassName())
//                .append(".").append(caller.getMethodName()).append("(): ")
//                .append(msg).toString();
        return caller.toString() + msg;

    }

}
