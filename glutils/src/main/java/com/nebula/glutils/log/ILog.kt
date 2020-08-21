package com.nebula.glutils.log

import android.content.Context

/**
 * @author: joey
 * @date: 2020/7/5
 */
interface ILog {

    fun init(context: Context, globalTag: String, isDebug: Boolean)

    fun flush()

    fun v(tag: String, message: String)

    fun d(tag: String, message: String)

    fun i(tag: String, message: String)

    fun w(tag: String, message: String)

    fun w(tag: String, msg: String, tr: Throwable)

    fun w(tag: String, tr: Throwable)

    fun e(tag: String, message: String)

    fun e(tag: String, msg: String, tr: Throwable)

    fun e(tag: String, tr: Throwable)
}