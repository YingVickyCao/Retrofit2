package com.hades.example.retrofit2;

/**
 * Mock Log implementation for testing on non android host.
 */
public final class Log {
    public static void d(String tag, String msg) {
        System.out.println(tag + ":" + msg);
    }

    public static void e(String tag, String msg) {
        System.err.println(tag + ":" + msg);
    }

    public static void d(String tag, String func, String msg) {
        System.out.println(tag + ":" + "" + func + "," + msg);
    }

    public static void d(String tag, String func, String msg, boolean ignoreTagFuncInfo) {
        if (!ignoreTagFuncInfo) {
            d(tag, func, msg);
            return;
        }
        System.out.println(msg);
    }

}
