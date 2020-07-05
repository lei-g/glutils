package com.nebula.glutils.log

import android.content.Context
import com.robot.logger2.Logger


/**
 * @author: joey
 * @date: 2020/7/5
 */
class LoggerImpl : ILog {

    override fun init(context: Context) {
        var customLogAdapter: Log4aAdapter = Log4aAdapter()

        Logger.init("nebula")
            .logAdapter(customLogAdapter)
            .methodCount(1)
            .hideThreadInfo()
            .methodOffset(2)
            .briefMode()
            //.logLevel(LogLevel.NONE)
    }

    override fun v(message: String) {
        Logger.v(message)
    }

    override fun d(message: String) {
        Logger.d(message)
    }

    override fun i(message: String) {
        Logger.i(message)
    }

    override fun w(message: String) {
        Logger.w(message)
    }

    override fun e(message: String) {
        Logger.e(message)
    }

}