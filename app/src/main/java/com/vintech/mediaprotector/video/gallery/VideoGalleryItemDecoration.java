package com.vintech.mediaprotector.video.gallery;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Vincent on 2016/5/27.
 */
public class VideoGalleryItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(1, 1, 1, 1);
    }
}
