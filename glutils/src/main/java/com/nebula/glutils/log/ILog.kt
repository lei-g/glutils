package com.nebula.glutils.log

import android.content.Context

/**
 * @author: joey
 * @date: 2020/7/5
 */
interface ILog {

    fun init(context: Context)

    fun v(message: String)

    fun d(message: String)

    fun i(message: String)

    fun w(message: String)

    fun e(message: String)

}