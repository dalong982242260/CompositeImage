package com.demo.compositeimage;

import android.os.Environment;

import java.io.File;

/**
 * Created by weiliang.zheng on 16/6/28.
 */
public class PathManager {

    private static final File APP_DIR = new File(Environment.getExternalStorageDirectory(),
            "dalong" + File.separator + "cut");

    private static final File LOG_DIR = new File(APP_DIR, "logs");
    private static final File CACHE_DIR = new File(APP_DIR, "cache");
    private static final File PHOTO_DIR = new File(APP_DIR, "photo");
    private static final File VIDEO_DIR = new File(APP_DIR, "video");

    private PathManager() {
    }

    public static File getLogDir() {
        if (!LOG_DIR.exists()) {
            LOG_DIR.mkdirs();
        }
        return LOG_DIR;
    }

    public static File getCacheDir() {
        if (!CACHE_DIR.exists()) {
            CACHE_DIR.mkdirs();
        }
        return CACHE_DIR;
    }

    public static File getPhotoDir() {
        if (!PHOTO_DIR.exists()) {
            PHOTO_DIR.mkdirs();
        }
        return PHOTO_DIR;
    }

    public static File getVideoDir() {
        if (!VIDEO_DIR.exists()) {
            VIDEO_DIR.mkdirs();
        }
        return VIDEO_DIR;
    }

    public static File getFileDir(File dir, String dirName) {
        File file = new File(dir, dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
