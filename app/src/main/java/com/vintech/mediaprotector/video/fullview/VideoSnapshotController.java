package com.vintech.mediaprotector.video.fullview;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

import com.vintech.mediaprotector.MainApplication;
import com.vintech.mediaprotector.R;
import com.vintech.mediaprotector.database.DatabaseHelper;
import com.vintech.mediaprotector.video.fullview.model.VideoSnapshotBean;
import com.vintech.mediaprotector.video.fullview.model.VideoSnapshotDbModel;
import com.vintech.mediaprotector.video.gallery.model.VideoBean;
import com.vintech.util.database.BaseDatabaseHelper;
import com.vintech.util.tool.ConvertUtil;
import com.vintech.util.tool.Machine;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by Vincent on 2016/2/29.
 */
public class VideoSnapshotController {
    public static final String SNAPSHOT_DIR = Machine.OBB_PATH + "/snapshot/";

    public void deleteSnapshot(VideoSnapshotBean bean) {
        VideoSnapshotDbModel model = MainApplication.getDBManager().getVideoSnapshotDbModel();
        model.deleteSnapshot(bean);
    }


    public void snapshot(final VideoBean video, long position) {
        final VideoSnapshotBean bean = new VideoSnapshotBean();
        bean.setPosition(position);
        bean.setVideo(video.getId());
        bean.setBitmap(SNAPSHOT_DIR + UUID.randomUUID().toString());

        DatabaseHelper.post(new Runnable() {
            @Override
            public void run() {
                boolean saved = snapshotToSDCard(video, bean);
                if (saved) {
                    MainApplication.getDBManager().getVideoSnapshotDbModel().insertSnapshot(bean);
                }
            }
        });
    }

    private boolean snapshotToSDCard(VideoBean videoBean, VideoSnapshotBean bean) {
        if (bean == null) {
            return false;
        }
        try {
            File f = new File(bean.getBitmap());
            File folder = f.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            int size = ConvertUtil.getPixel(R.dimen.snapshot_bitmap_width);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(videoBean.getEncryptPath());
            long posUs = bean.getPosition() * 1000;
            Bitmap bitmap = retriever.getFrameAtTime(posUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            bean.snapshot = Bitmap.createScaledBitmap(bitmap, size, bitmap.getHeight() * size / bitmap.getWidth(), false);
            boolean compress = bean.snapshot.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(bean.getBitmap()));
            return compress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
