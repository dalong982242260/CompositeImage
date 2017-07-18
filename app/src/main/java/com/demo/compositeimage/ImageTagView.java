package com.demo.compositeimage;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by dalong  on 2017/5/24.
 */

public class ImageTagView extends AppCompatImageView {
    public ImageTagView(Context context) {
        this(context, null);
    }

    public ImageTagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.FIT_XY);
    }
}
