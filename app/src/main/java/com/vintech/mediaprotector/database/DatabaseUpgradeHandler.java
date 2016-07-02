package com.vintech.mediaprotector.database;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vintech.mediaprotector.video.fullview.model.VideoSnapshotBean;

import java.sql.SQLException;

/**
 * Created by Vincent on 2016/6/18.
 */
public class DatabaseUpgradeHandler {
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connection, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return;
        }

        switch (oldVersion) {
            case 1:
                from1(connection);
                break;
        }

        onUpgrade(database, connection, oldVersion + 1, newVersion);
    }

    private void from1(ConnectionSource connection) {
    }
}
