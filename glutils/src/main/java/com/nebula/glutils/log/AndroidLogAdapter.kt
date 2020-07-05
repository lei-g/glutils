package com.nebula.glutils.log

import android.util.Log
import com.robot.logger2.LogAdapter

/**
 * @author: joey
 * @date: 2020/7/5
 */
class AndroidLogAdapter : LogAdapter {

    private var logLevel = Log.VERBOSE

    override fun v(tag: String, msg: String) {
        logLevel = Log.VERBOSE
        logPrint(logLevel, tag, msg)
    }

    override fun d(tag: String, msg: String) {
        logLevel = Log.DEBUG
        logPrint(logLevel, tag, msg)
    }

    override fun i(tag: String, msg: String) {
        logLevel = Log.INFO
        logPrint(logLevel, tag, msg)
    }

    override fun w(tag: String, msg: String) {
        logLevel = Log.WARN
        logPrint(logLevel, tag, msg)
    }

    override fun e(tag: String, msg: String) {
        logLevel = Log.ERROR
        logPrint(logLevel, tag, msg)
    }

    override fun wtf(tag: String, msg: String) {
        logLevel = Log.ASSERT
        logPrint(logLevel, tag, msg)
    }

    private fun logPrint(logLevel: Int, customTag: String, msg: Any) {
        Log.println(logLevel, customTag, "$msg")
    }

}