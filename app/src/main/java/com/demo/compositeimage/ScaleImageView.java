package com.demo.compositeimage;

import android.content.Context;
import android.util.AttributeSet;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by dalong  on 2017/5/24.
 */

public class ScaleImageView extends PhotoView {

    public ScaleImageView(Context context) {
        this(context,null);
    }

    public ScaleImageView(Context context, AttributeSet attr) {
        this(context, attr,0);
    }

    public ScaleImageView(Context context, AttributeSet attr, int defStyle) {
        this(context, attr, defStyle,0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        setScaleType(ScaleType.CENTER_CROP);

    }
}
