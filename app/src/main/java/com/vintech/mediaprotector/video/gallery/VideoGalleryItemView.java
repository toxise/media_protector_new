package com.vintech.mediaprotector.video.gallery;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Vincent on 2016/5/27.
 */
public class VideoGalleryItemView extends FrameLayout {
    public AppCompatImageView mImageView;
    public AppCompatCheckBox mCheckBox;

    public VideoGalleryItemView(Context context) {
        super(context);
        mImageView = new AppCompatImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(mImageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        mCheckBox = new AppCompatCheckBox(context);
        mCheckBox.setVisibility(GONE);
        addView(mCheckBox, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        if (widthSpec != MeasureSpec.UNSPECIFIED) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
