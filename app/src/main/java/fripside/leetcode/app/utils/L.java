package fripside.leetcode.app.utils;

import android.util.Log;

import fripside.leetcode.app.BuildConfig;


/**
 * Created by fripside on 1/14/16.
 */
public class L {
//    private static final boolean DEBUG = true;

    public static void i(String tag, String log) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, log);
        }
    }

    public static void d(String tag, String log) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, log);
        }
    }

    public static void e(String tag, String log) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, log);
        }
    }
}
