package com.demo.compositeimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * 合成布局
 * Created by dalong  on 2017/5/24.
 */

public class ImageSynthesisLayout extends FrameLayout {

    private final String TAG = "ImageSynthesisLayout";
    private Context mContext;
    private SynthesisInfo synthesisInfo;
    private float radioH;
    private Bitmap bgIcon;
    private int carIcon;
    private int logoIcon;
    private ImageInfo bgImgInfo;

    public ImageSynthesisLayout(@NonNull Context context) {
        this(context, null);
    }

    public ImageSynthesisLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageSynthesisLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    public void setSynthesisData(SynthesisInfo synthesisInfo, Bitmap imgBg, int carIcon, int logoIcon) {
        this.synthesisInfo = synthesisInfo;
        this.bgIcon = imgBg;
        this.carIcon = carIcon;
        this.logoIcon = logoIcon;
        addChildView();
    }

    private void addChildView() {
        removeAllViews();
        addImgView();
        addTextView();
    }

    /**
     * 添加图片
     */
    private void addImgView() {
        ScaleImageView carView = new ScaleImageView(mContext);
        carView.setBackgroundColor(Color.RED);

        Glide.with(mContext)
                .load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3687912934,3231558600&fm=26&gp=0.jpg")
                .into(carView);

        //在设置glide之后设置tag
        carView.setTag(SynthesisInfoUtils.getCarImgInfo(synthesisInfo));
        addView(carView);


        ImageTagView background = new ImageTagView(mContext);
        bgImgInfo = SynthesisInfoUtils.getBgImgInfo(synthesisInfo);
        background.setTag(bgImgInfo);
        background.setImageBitmap(bgIcon);
        addView(background);


        ImageTagView logoView = new ImageTagView(mContext);
        Glide.with(mContext)
                .load("http://img.souche.com/20160415/png/b7ceceee53c4669e6150cae23ca35e0a.png")
                .into(logoView);

        //在设置glide之后设置tag
        logoView.setTag(SynthesisInfoUtils.getLogoImgInfo(synthesisInfo));
        addView(logoView);

        ImageTagView headView = new ImageTagView(mContext);
        Glide.with(mContext)
                .load("http://img.souche.com/files/potrait/potrait_1478311083087.png")
                .transform(new GlideCircleTransform(mContext))
                .into(headView);
        //在设置glide之后设置tag
        headView.setTag(SynthesisInfoUtils.getHeadImgInfo(synthesisInfo));
        addView(headView);
    }

    /**
     * 添加文本view
     */
    private void addTextView() {
        if (synthesisInfo != null && synthesisInfo.textList != null && synthesisInfo.textList.size() > 0) {
            for (int i = 0; i < synthesisInfo.textList.size(); i++) {
                TextView textView = new TextView(mContext);
                textView.setTag(synthesisInfo.textList.get(i));
                addView(textView);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        float sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        float sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        //底图的宽
        float originW = bgImgInfo.width;
        //底图的高
        float originH = bgImgInfo.height;
        //缩放比例 这里以高来计算
        radioH = sizeHeight / originH;
        //这里修改宽  因为根据了外部设定的高来缩放 所以这里以底部计算的宽为控件的宽
        sizeWidth = (int) (originW * radioH);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof ImageTagView) {
                //根据比例进行缩放
                FrameLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
                ImageInfo imageInfo = (ImageInfo) view.getTag();
                if (null != imageInfo) {
                    //根据缩放计算新的宽高及向上向左间距
                    float offsetToLeft = imageInfo.offsetToLeft * radioH;
                    float offsetToTop = imageInfo.offsetToTop * radioH;
                    float newW = imageInfo.width * radioH;
                    float newH = imageInfo.height * radioH;
                    //设置底图新的宽高
                    lp.leftMargin = (int) offsetToLeft;
                    lp.topMargin = (int) offsetToTop;
                    lp.width = (int) newW;
                    lp.height = (int) newH;
                    view.setLayoutParams(lp);
                }
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
            } else if (view instanceof ScaleImageView) {
                //根据比例进行缩放
                FrameLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
                ImageInfo imageInfo = (ImageInfo) view.getTag();
                if (null != imageInfo) {
                    //缩放下carIcon
                    float offsetToLeft = imageInfo.offsetToLeft * radioH;
                    float offsetToTop = imageInfo.offsetToTop * radioH;
                    float newW = imageInfo.width * radioH;
                    float newH = imageInfo.height * radioH;

                    lp.leftMargin = (int) offsetToLeft;
                    lp.topMargin = (int) offsetToTop;
                    lp.width = (int) newW;
                    lp.height = (int) newH;
                    view.setLayoutParams(lp);
                }
                measureChild(view, widthMeasureSpec, heightMeasureSpec);

            } else if (view instanceof TextView && synthesisInfo != null) {
                TextInfo textInfo = (TextInfo) view.getTag();
                if (textInfo != null) {
                    ((TextView) view).setText(textInfo.text);
                    ((TextView) view).setTextColor(Color.parseColor(textInfo.textColor));
                    ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, radioH * textInfo.textSize);
                    FrameLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    float offsetToLeft = textInfo.offsetToLeft * radioH;
                    float offsetToTop = textInfo.offsetToTop * radioH;
                    float newW = textInfo.width * radioH;
                    float newH = textInfo.height * radioH;
                    lp.leftMargin = (int) offsetToLeft;
                    lp.topMargin = (int) offsetToTop;
//                    lp.width = (int) newW;
//                    lp.height = (int) newH;
                    view.setLayoutParams(lp);
                }

                measureChild(view, widthMeasureSpec, heightMeasureSpec);
            } else {
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
            }
            setMeasuredDimension((int) sizeWidth, (int) sizeHeight);
        }
    }
}
