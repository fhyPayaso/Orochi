package com.fhypayaso.orochi.utils;

import java.io.File;

public class FileUtil {

    private FileUtil() {
    }


    /**
     * 获取文件类型
     *
     * @param file 文件
     * @return 文件后缀
     */
    public static String getFileType(File file) {
        if (file == null) {
            return null;
        }
        return getFileType(file.getName());
    }


    /**
     * 获取文件类型
     *
     * @param fileName 文件名称
     * @return 文件后缀
     */
    public static String getFileType(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }











}
