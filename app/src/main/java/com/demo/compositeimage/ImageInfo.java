package com.demo.compositeimage;

/**
 * 图片位置信息
 * Created by dalong  on 2017/5/24.
 */

public class ImageInfo {
    public static final String VIEW_TYPE_CAR = "carIcon";
    public static final String VIEW_TYPE_BG = "background";
    public static final String VIEW_TYPE_LOG = "logoIcon";
    public static final String VIEW_TYPE_HEAD = "headIcon";
    public int offsetToLeft;
    public int offsetToTop;
    public int width;
    public int height;
    public String viewType;

    @Override
    public String toString() {
        return "ImageInfo{" +
                "offsetToLeft=" + offsetToLeft +
                ", offsetToTop=" + offsetToTop +
                ", width=" + width +
                ", height=" + height +
                ", viewType='" + viewType + '\'' +
                '}';
    }

    /**
     * 是否是车辆图
     *
     * @return
     */
    public boolean isCarIcon() {
        return viewType.equals(VIEW_TYPE_CAR);
    }

    /**
     * 是否是底部背景图
     *
     * @return
     */
    public boolean isBgIcon() {
        return viewType.equals(VIEW_TYPE_BG);
    }

    /**
     * 是否是店铺logo图
     *
     * @return
     */
    public boolean isLogoIcon() {
        return viewType.equals(VIEW_TYPE_LOG);
    }
}
