package com.yichuizi.tiktik.media;

import android.support.annotation.NonNull;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 16:29
 * 描述：
 */
public class VideoPlayerManager {
    private MediaController mMediaController;

    private VideoPlayerManager() {
    }

    private static class Holder {
        static VideoPlayerManager holder = new VideoPlayerManager();
    }

    public static VideoPlayerManager getInstance() {
        return Holder.holder;
    }

    public void setCurrentVideoPlayer(@NonNull MediaController mediaController) {
        if (mMediaController == null || !mMediaController.toString().equals(mediaController.toString())) {
            releaseVideoPlayer();
        }
        this.mMediaController = mediaController;
    }

    public void startPlay() {
        if (mMediaController != null) {
            mMediaController.startPlay();
        }
    }

    public void releaseVideoPlayer() {
        if (mMediaController != null) {
            mMediaController.stopPlay();
            mMediaController = null;
        }
    }

    public MediaController getMediaController() {
        return mMediaController;
    }
}
