package com.nebula.glutils

import java.io.*

/**
 * @author: joey
 * @function: 文件操作、查询
 * @date: 2017/6/23
 */
object FileUtils {
    /**
     * @Description 判断文件是否存在
     * @param filePath 文件名，包含绝对路径
     * @return 存在返回true，不存在返回false
     */
    fun isFileExist(filePath: String?): Boolean {
        try {
            val f = File(filePath)
            if (!f.exists()) {
                return false
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    /**
     * @Description 复制单个文件
     * @param oldPath String 原文件路径
     * @param newPath String 复制后路径
     * @return 字节数 文件大小
     */
    fun copyFile(oldPath: String?, newPath: String?): Int {
        var bytesum = 0
        var byteread = 0
        var inStream: InputStream? = null
        val oldfile = File(oldPath)
        if (!oldfile.exists()) {
            return 0
        }
        if (!oldfile.isFile) {
            return 0
        }
        if (!oldfile.canRead()) {
            return 0
        }
        try {
            inStream = FileInputStream(oldPath)
            val fs = FileOutputStream(newPath)
            val buffer = ByteArray(1444)
            while (inStream.read(buffer).also { byteread = it } != -1) {
                bytesum += byteread
                fs.write(buffer, 0, byteread)
            }
            inStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return 0
        } catch (e: IOException) {
            e.printStackTrace()
            return 0
        }
        return bytesum
    }

    /**
     * 获取路径中文件的名字，不包含后缀
     * @param pathandname
     * @return
     */
    fun getFileName(pathandname: String): String? {
        val start = pathandname.lastIndexOf("/")
        val end = pathandname.lastIndexOf(".")
        return if (start != -1 && end != -1) {
            pathandname.substring(start + 1, end)
        } else {
            null
        }
    }

    /**
     * 获取路径中文件的名字，包含后缀
     * @param pathandname
     * @return
     */
    fun getFileNameWithSuffix(pathandname: String): String? {
        val start = pathandname.lastIndexOf("/")
        return if (start != -1) {
            pathandname.substring(start + 1, pathandname.length)
        } else {
            null
        }
    }

    /**
     * 获取文件路径的路径部分
     * @param pathandname
     * @return
     */
    fun getFilePath(pathandname: String): String? {
        val start = pathandname.lastIndexOf("/")
        return if (start != -1) {
            pathandname.substring(0, start)
        } else {
            null
        }
    }

    /**
     * 内容逐行写入文件
     * @param file 文件的名字
     * @param content 追加的内容
     */
    fun writeToFile(file: String, content: String?) {
        val path = getFilePath(file) ?: return
        if (!isFileExist(path)) {
            File(path).mkdirs()
        }
        if (!isFileExist(file)) {
            File(file)
        }
        var out: BufferedWriter? = null
        try {
            out = BufferedWriter(OutputStreamWriter(
                    FileOutputStream(file, true)))
            out.write(content)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                out!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 清除文件内容
     * @param fileName
     */
    fun clearInfoForFile(fileName: String?) {
        val file = File(fileName)
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
            val fileWriter = FileWriter(file)
            fileWriter.write("")
            fileWriter.flush()
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}