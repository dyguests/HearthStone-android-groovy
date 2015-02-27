package com.fanhl.util

/**
 * Created by fanhl on 15/1/7.
 */
class Lg {
    /** 是否显示Log信息 */
    public static boolean isDebug = true

    static v = { String tag, String msg -> if (isDebug) android.util.Log.v(tag, msg) }
    static d = { String tag, String msg -> if (isDebug) android.util.Log.d(tag, msg) }
    static i = { String tag, String msg -> if (isDebug) android.util.Log.i(tag, msg) }
    static w = { String tag, String msg -> if (isDebug) android.util.Log.w(tag, msg) }
    static e = { String tag, String msg -> if (isDebug) android.util.Log.e(tag, msg) }
}