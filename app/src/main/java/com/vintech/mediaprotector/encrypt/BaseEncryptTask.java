package com.vintech.mediaprotector.encrypt;

/**
 * Created by Vincent on 2016/5/28.
 */
public abstract class BaseEncryptTask implements Runnable {
    @Override
    public void run() {
        onStart();
        EncryptTaskManager.onTaskFinished(this);
    }

    public abstract void onStart();
}
