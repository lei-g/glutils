package com.nebula.glutils.log

import android.content.Context
import me.pqpo.librarylog4a.Log4a

/**
 * @author: joey
 * @date: 2020/7/5
 */
object LogUtils {

    lateinit var mImpl: ILog

    /*
     * why do this?
     * answer : Interface-oriented programming
     *
     * if we change log realization only need pass in new parameters, and id doesn't affect
     * our upper level code.
     * Because we are interface oriented.
     */
    fun setImpl(impl: ILog) {
        mImpl = impl
    }

    @JvmStatic
    fun init(context: Context, globalTag: String, isDebug: Boolean) {
        setImpl(Log4aImpl())
        mImpl.init(context, globalTag, isDebug)
    }

    @JvmStatic
    fun flush() {
        mImpl.flush()
    }

    @JvmStatic
    fun v(tag: String, message: String) {
        mImpl.v(tag, message)
    }

    @JvmStatic
    fun d(tag: String, message: String) {
        mImpl.d(tag, message)
    }

    @JvmStatic
    fun i(tag: String, message: String) {
        mImpl.i(tag, message)
    }

    @JvmStatic
    fun w(tag: String, message: String) {
        mImpl.w(tag, message)
    }

    @JvmStatic
    fun w(tag: String, message: String, tr: Throwable) {
        mImpl.w(tag, message, tr)
    }

    @JvmStatic
    fun w(tag: String, tr: Throwable) {
        mImpl.w(tag, tr)
    }

    @JvmStatic
    fun e(tag: String, message: String) {
        mImpl.e(tag, message)
    }

    @JvmStatic
    fun e(tag: String, message: String, tr: Throwable) {
        mImpl.e(tag, message, tr)
    }

    @JvmStatic
    fun e(tag: String, tr: Throwable) {
        mImpl.e(tag, tr)
    }

}