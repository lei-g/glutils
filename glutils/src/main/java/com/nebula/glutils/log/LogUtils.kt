package com.nebula.glutils.log

import android.content.Context

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
    fun init(context: Context) {
        LogInit.init(context)
        setImpl(LoggerImpl())
        mImpl.init(context)
    }

    @JvmStatic
    fun v(message: String) {
        mImpl.d(message)
    }

    @JvmStatic
    fun d(message: String) {
        mImpl.d(message)
    }

    @JvmStatic
    fun i(message: String) {
        mImpl.d(message)
    }

    @JvmStatic
    fun w(message: String) {
        mImpl.w(message)
    }

    @JvmStatic
    fun e(message: String) {
        mImpl.e(message)
    }

}