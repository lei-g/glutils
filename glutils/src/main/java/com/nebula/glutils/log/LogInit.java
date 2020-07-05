package com.nebula.glutils.log;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.nebula.glutils.BuildConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.core.content.ContextCompat;
import me.pqpo.librarylog4a.Level;
import me.pqpo.librarylog4a.Log4a;
import me.pqpo.librarylog4a.LogData;
import me.pqpo.librarylog4a.appender.AndroidAppender;
import me.pqpo.librarylog4a.appender.FileAppender;
import me.pqpo.librarylog4a.formatter.DateFileFormatter;
import me.pqpo.librarylog4a.interceptor.Interceptor;
import me.pqpo.librarylog4a.logger.AppenderLogger;

/**
 * Created by joey on 2020/7/4.
 */
public class LogInit {

    public static final int BUFFER_SIZE = 1024 * 400; //400k
    public static final String GLOBAL_TAG = "GL";

    public static void init(Context context) {
        int level;
        if (BuildConfig.DEBUG) {
            level = Level.DEBUG;
        } else {
            level = Level.INFO;
        }

        Interceptor wrapInterceptor = new Interceptor() {
            @Override
            public boolean intercept(LogData logData) {
                logData.tag = GLOBAL_TAG + "-" + logData.tag + " ";
                return true;
            }
        };

        AndroidAppender androidAppender = new AndroidAppender.Builder()
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .create();

        File cache = createCacheDir(context);
        String bufferPath = cache.getAbsolutePath() + File.separator + ".logCache";
        String time = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
        String logPath;
        if (checkSDPermission(context)) {
            // has write SD permission
            String logDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gl/logs";
            File log = createLogDir(logDir);
            logPath = log.getAbsolutePath() + File.separator + time + ".txt";
        } else {
            // no write SD permission
            logPath = cache.getAbsolutePath() + File.separator + time + ".txt";
        }
        Log4a.i(GLOBAL_TAG, "logPath = " + logPath);

        FileAppender fileAppender = new FileAppender.Builder(context)
                .setLogFilePath(logPath)
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .setBufferFilePath(bufferPath)
                .setFormatter(new DateFileFormatter())
                .setCompress(false)
                .setBufferSize(BUFFER_SIZE)
                .create();

        AppenderLogger logger;
        // release doesn't print log to console.
        if (BuildConfig.DEBUG) {
            logger = new AppenderLogger.Builder()
                    .addAppender(androidAppender)
                    .addAppender(fileAppender)
                    .create();
        } else {
            logger = new AppenderLogger.Builder()
                    .addAppender(fileAppender)
                    .create();
        }

        Log4a.setLogger(logger);
    }

    private static boolean checkSDPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private static File createLogDir(String fileName) {
        File dir = new File(fileName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private static File createCacheDir(Context context) {
        File log = context.getExternalFilesDir("logs");
        if (log == null) {
            log = new File(context.getFilesDir(), "logs");
        }
        if (!log.exists()) {
            log.mkdir();
        }
        return log;
    }

}
