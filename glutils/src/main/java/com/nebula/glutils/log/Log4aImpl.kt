package com.nebula.glutils.log

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import me.pqpo.librarylog4a.Level
import me.pqpo.librarylog4a.Log4a
import me.pqpo.librarylog4a.appender.AndroidAppender
import me.pqpo.librarylog4a.appender.FileAppender
import me.pqpo.librarylog4a.formatter.DateFileFormatter
import me.pqpo.librarylog4a.interceptor.Interceptor
import me.pqpo.librarylog4a.logger.AppenderLogger
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: joey
 * @date: 2020/7/5
 */
@Suppress("DEPRECATION")
class Log4aImpl : ILog {

    companion object {
        const val BUFFER_SIZE = 1024 * 400 //400k
    }

    override fun init(context: Context, globalTag: String, isDebug: Boolean) {
        val level: Int
        level = if (isDebug) {
            Level.DEBUG
        } else {
            Level.INFO
        }
        val wrapInterceptor = Interceptor { logData ->
            logData.tag = globalTag + "-" + logData.tag + " "
            true
        }
        val androidAppender = AndroidAppender.Builder()
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .create()
        val cache = createCacheDir(context)
        val bufferPath = cache.absolutePath + File.separator + ".logCache"
        val time = SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(Date())
        val logPath: String
        logPath = if (checkSDPermission(context)) {
            // has write SD permission
            val logDir = Environment.getExternalStorageDirectory().absolutePath + File.separator + context.opPackageName + File.separator + "logs"
            val log = createLogDir(logDir)
            log.absolutePath + File.separator + time + ".txt"
        } else {
            // no write SD permission
            cache.absolutePath + File.separator + time + ".txt"
        }
        Log4a.i(globalTag, "logPath = $logPath")
        val fileAppender = FileAppender.Builder(context)
                .setLogFilePath(logPath)
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .setBufferFilePath(bufferPath)
                .setFormatter(DateFileFormatter())
                .setCompress(false)
                .setBufferSize(Companion.BUFFER_SIZE)
                .create()
        val logger: AppenderLogger
        // release doesn't print log to console.
        logger = if (isDebug) {
            AppenderLogger.Builder()
                    .addAppender(androidAppender)
                    .addAppender(fileAppender)
                    .create()
        } else {
            AppenderLogger.Builder()
                    .addAppender(fileAppender)
                    .create()
        }
        Log4a.setLogger(logger)
    }

    private fun checkSDPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                false
            } else {
                true
            }
        } else false
    }

    private fun createLogDir(fileName: String): File {
        val dir = File(fileName)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    private fun createCacheDir(context: Context): File {
        var log = context.getExternalFilesDir("logs")
        if (log == null) {
            log = File(context.filesDir, "logs")
        }
        if (!log.exists()) {
            log.mkdir()
        }
        return log
    }

    override fun flush() {
        Log4a.flush()
    }

    override fun v(tag: String, message: String) {
        Log4a.v(tag, message)
    }

    override fun d(tag: String, message: String) {
        Log4a.d(tag, message)
    }

    override fun i(tag: String, message: String) {
        Log4a.i(tag, message)
    }

    override fun w(tag: String, message: String) {
        Log4a.w(tag, message)
    }

    override fun w(tag: String, msg: String, tr: Throwable) {
        Log4a.w(tag, msg, tr)
    }

    override fun w(tag: String, tr: Throwable) {
        Log4a.w(tag, tr)
    }

    override fun e(tag: String, message: String) {
        Log4a.e(tag, message)
    }

    override fun e(tag: String, msg: String, tr: Throwable) {
        Log4a.e(tag, msg, tr)
    }

    override fun e(tag: String, tr: Throwable) {
        Log4a.e(tag, tr)
    }

}