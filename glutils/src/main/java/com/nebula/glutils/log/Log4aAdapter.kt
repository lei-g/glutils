package com.nebula.glutils.log

import android.util.Log
import com.robot.logger2.LogAdapter
import me.pqpo.librarylog4a.Log4a

/**
 * @author: joey
 * @date: 2020/7/5
 */
class Log4aAdapter : LogAdapter {

    private var logLevel = Log.VERBOSE

    override fun v(tag: String, msg: String) {
        Log4a.v(tag, msg)
    }

    override fun d(tag: String, msg: String) {
        Log4a.d(tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log4a.i(tag, msg)
    }

    override fun w(tag: String, msg: String) {
        Log4a.w(tag, msg)
    }

    override fun e(tag: String, msg: String) {
        Log4a.e(tag, msg)
    }

    override fun wtf(p0: String?, p1: String?) {
        TODO("Not yet implemented")
    }

}