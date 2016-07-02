package com.vintech.mediaprotector.photo.fullview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.vintech.mediaprotector.MainApplication;
import com.vintech.mediaprotector.R;
import com.vintech.mediaprotector.framework.IWorkspaceFrame;
import com.vintech.mediaprotector.photo.gallery.model.PhotoBean;
import com.vintech.mediaprotector.util.PGW;

import java.util.List;

/**
 * Created by Vincent on 2016/5/31.
 */
public class FullPhotoActivity extends AppCompatActivity {
    public static final String BUNDLE_POS_PHOTO = "bundle_pos_photo";
    private ViewPager mGallery;
    private FullPhotoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        int index = getIntent().getIntExtra(BUNDLE_POS_PHOTO, 0);
        setContentView(R.layout.photo_full_gallery_activity);

        mGallery = (ViewPager) findViewById(R.id.gallery);

        mAdapter = new FullPhotoAdapter();
        List<PhotoBean> photos = MainApplication.getDBManager().getPhotoModel().getPhotos();
        mAdapter.addPhotoBean(photos);
        mGallery.setAdapter(mAdapter);
        mGallery.setCurrentItem(index);
    }
}
