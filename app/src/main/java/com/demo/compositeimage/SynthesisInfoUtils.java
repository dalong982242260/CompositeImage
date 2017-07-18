package com.demo.compositeimage;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dalong  on 2017/5/24.
 */

public class SynthesisInfoUtils {

    public static SynthesisInfo getPuzzleInfo(Context context, String fileName) {
        String json = readFromAssets(context, fileName);
        Gson gson = new Gson();
        SynthesisInfo synthesisInfo = gson.fromJson(json, SynthesisInfo.class);
        if (synthesisInfo != null) {
            System.out.print("error: hi,dalong no data !");
        }
        return synthesisInfo;
    }

    private static String readFromAssets(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            return readTextFromSDcard(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }


    /**
     * 从Assets中读取图片
     */
    public static Bitmap getImageFromAssetsFile(Context mContext, String fileName) {
        Bitmap image = null;
        AssetManager am = mContext.getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    public static ImageInfo getBgImgInfo(SynthesisInfo info) {
        if (info != null) {
            for (ImageInfo infoBean : info.imageList) {
                if (infoBean.viewType.equals(ImageInfo.VIEW_TYPE_BG)) {
                    return infoBean;
                }
            }
        }

        return null;
    }

    public static ImageInfo getCarImgInfo(SynthesisInfo info) {
        if (info != null) {
            for (ImageInfo infoBean : info.imageList) {
                if (infoBean.viewType.equals(ImageInfo.VIEW_TYPE_CAR)) {
                    return infoBean;
                }
            }
        }
        return null;
    }
    public static ImageInfo getLogoImgInfo(SynthesisInfo info) {
        if (info != null) {
            for (ImageInfo infoBean : info.imageList) {
                if (infoBean.viewType.equals(ImageInfo.VIEW_TYPE_LOG)) {
                    return infoBean;
                }
            }
        }
        return null;
    }
    public static ImageInfo getHeadImgInfo(SynthesisInfo info) {
        if (info != null) {
            for (ImageInfo infoBean : info.imageList) {
                if (infoBean.viewType.equals(ImageInfo.VIEW_TYPE_HEAD)) {
                    return infoBean;
                }
            }
        }
        return null;
    }




    /**
     * 截图
     * @return
     */
    public static Bitmap getViewDraw(LinearLayout cutLayout) {
        int sumHeight = 0;
        for (int i = 0; i < cutLayout.getChildCount(); i++) {
            sumHeight += cutLayout.getChildAt(i).getHeight();
        }
        Bitmap bmp = Bitmap.createBitmap(cutLayout.getWidth(), sumHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(0xfff7f7f7);
        cutLayout.draw(canvas);
        return bmp;
    }

    public static void saveBitmap(String path, Bitmap bitmap, int quality) {
        try {
            File originalFile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(originalFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
