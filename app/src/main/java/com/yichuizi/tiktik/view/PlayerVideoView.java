package com.yichuizi.tiktik.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.yichuizi.tiktik.bean.VideoBean;
import com.yichuizi.tiktik.media.MediaController;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/5 17:41
 * 描述：
 */
public class PlayerVideoView extends RecyclerViewHeadView {
    private VideoBean mVideoBean;
    private MediaController mMediaController;

    public PlayerVideoView(@NonNull Context context) {
        super(context);
    }

    public PlayerVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setVideoBean(VideoBean videoBean) {
        this.mVideoBean = videoBean;
        mMediaController = new MediaController();
        mMediaController.setVideoView(mPlayerView, videoBean.getmLink());
        mMediaController.startPlay();
    }

    public MediaController getmMediaController() {
        return mMediaController;
    }
}
