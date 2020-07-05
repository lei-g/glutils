package com.nebula.glutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * @author: joey
 * @function: 文件操作、查询
 * @date: 2017/6/23
 */

public class FileUtils {

    /**
     * @Description 判断文件是否存在
     * @param filePath 文件名，包含绝对路径
     * @return 存在返回true，不存在返回false
     */
    public static boolean isFileExist(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @Description 复制单个文件
     * @param oldPath String 原文件路径
     * @param newPath String 复制后路径
     * @return 字节数 文件大小
     */
    public static int copyFile(String oldPath, String newPath) {
        int bytesum = 0;
        int byteread = 0;
        InputStream inStream = null;
        File oldfile = new File(oldPath);

        if (!oldfile.exists()) {
            return 0;
        }
        if (!oldfile.isFile()) {
            return 0;
        }
        if (!oldfile.canRead()) {
            return 0;
        }

        try {
            inStream = new FileInputStream(oldPath);
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return bytesum;
    }

    /**
     * 获取路径中文件的名字，不包含后缀
     * @param pathandname
     * @return
     */
    public static String getFileName(String pathandname){
        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }
    }

    /**
     * 获取路径中文件的名字，包含后缀
     * @param pathandname
     * @return
     */
    public static String getFileNameWithSuffix(String pathandname){
        int start = pathandname.lastIndexOf("/");
        if (start != -1) {
            return pathandname.substring(start + 1, pathandname.length());
        } else {
            return null;
        }
    }

    /**
     * 获取文件路径的路径部分
     * @param pathandname
     * @return
     */
    public static String getFilePath(String pathandname){
        int start = pathandname.lastIndexOf("/");
        if (start != -1) {
            return pathandname.substring(0, start);
        } else {
            return null;
        }
    }

    /**
     * 内容逐行写入文件
     * @param file 文件的名字
     * @param content 追加的内容
     */
    public static void writeToFile(String file, String content) {
        String path = getFilePath(file);
        if (path == null) {
            return;
        }
        if (!isFileExist(path)) {
            new File(path).mkdirs();
        }
        if (!isFileExist(file)) {
            new File(file);
        }
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清除文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
