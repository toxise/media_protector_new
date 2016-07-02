package com.vintech.mediaprotector.photo.fullview;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Vincent on 2016/5/27.
 */
public class FullPhotoItemView extends AppCompatImageView {

    public FullPhotoItemView(Context context) {
        super(context);
        setScaleType(ScaleType.CENTER_INSIDE);
        setPadding(3, 3, 3, 3);
    }
}
