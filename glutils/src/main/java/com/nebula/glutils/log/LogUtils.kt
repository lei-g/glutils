package com.nebula.glutils.log

import android.content.Context

/**
 * @author: joey
 * @date: 2020/7/5
 */
object LogUtils {

    lateinit var mImpl: ILog
    var mTag: String = ""

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
    fun init(context: Context, isDebug: Boolean) {
        setImpl(Log4aImpl())
        mImpl.init(context, isDebug)
    }

    @JvmStatic
    fun setTag(tag: String) {
        mTag = tag
    }

    @JvmStatic
    fun v(message: String) {
        mImpl.d(mTag, message)
    }

    @JvmStatic
    fun d(message: String) {
        mImpl.d(mTag, message)
    }

    @JvmStatic
    fun i(message: String) {
        mImpl.d(mTag, message)
    }

    @JvmStatic
    fun w(message: String) {
        mImpl.w(mTag, message)
    }

    @JvmStatic
    fun e(message: String) {
        mImpl.e(mTag, message)
    }

}