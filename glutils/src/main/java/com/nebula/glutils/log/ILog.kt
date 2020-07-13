package com.nebula.glutils.log

import android.content.Context

/**
 * @author: joey
 * @date: 2020/7/5
 */
interface ILog {

    fun init(context: Context, isDebug: Boolean)

    fun v(tag: String, message: String)

    fun d(tag: String, message: String)

    fun i(tag: String, message: String)

    fun w(tag: String, message: String)

    fun e(tag: String, message: String)

}