package com.demo.compositeimage;

/**
 * Created by dalong  on 2017/5/24.
 */

public class TextInfo {

    public int offsetToLeft;
    public int offsetToTop;
    public int width;
    public int height;
    public String viewType;
    public String text;
    public int textSize;
    public String textColor;

    @Override
    public String toString() {
        return "TextInfo{" +
                "offsetToLeft=" + offsetToLeft +
                ", offsetToTop=" + offsetToTop +
                ", width=" + width +
                ", height=" + height +
                ", viewType='" + viewType + '\'' +
                ", text='" + text + '\'' +
                ", textSize=" + textSize +
                ", textColor='" + textColor + '\'' +
                '}';
    }
}
