package com.vintech.mediaprotector.encrypt;

import android.media.MediaScannerConnection;
import android.provider.MediaStore;

import com.vintech.mediaprotector.MainApplication;
import com.vintech.mediaprotector.photo.PhotoEvents;
import com.vintech.mediaprotector.photo.gallery.model.PhotoBean;
import com.vintech.mediaprotector.photo.gallery.model.PhotoDbModel;
import com.vintech.mediaprotector.video.VideoEvents;
import com.vintech.mediaprotector.video.gallery.model.VideoBean;
import com.vintech.mediaprotector.video.gallery.model.VideoDbModel;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * Created by Vincent on 2016/5/28.
 */
public class VideoEncryptTask extends BaseEncryptTask {
    public static final String ENCRYPT_NAME = ".mpvideo";
    private String mOriPath;

    public VideoEncryptTask(String str) {
        mOriPath = str;
    }

    @Override
    public void onStart() {
        File oriFile = new File(mOriPath);
        File toFile = new File(mOriPath + ENCRYPT_NAME);
        oriFile.renameTo(toFile);

        VideoDbModel videoModel = MainApplication.getDBManager().getVideoModel();

        VideoBean bean = new VideoBean();
        bean.setOriPath(oriFile.getPath());
        bean.setEncryptPath(toFile.getPath());
        videoModel.insertVideo(bean);
        EventBus.getDefault().post(new VideoEvents.EventEncryptVideoChanged(bean, VideoEvents.EventEncryptVideoChanged.TYPE_ADD));

        try {
            MainApplication.getContext().getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MediaStore.Video.Media.DATA + "=?", new String[]{mOriPath});
            MediaScannerConnection.scanFile(MainApplication.getContext(), new String[]{mOriPath}, null, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
