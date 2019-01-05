package com.yichuizi.tiktik.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;
import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.bean.UserBean;
import com.yichuizi.tiktik.bean.VideoBean;
import com.yichuizi.tiktik.media.MediaController;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 12:08
 * 描述：
 */
public class VideoItemView extends FrameLayout {

    private MediaController mMediaController;

    private VideoBean mVideoBean;
    private UserBean mUserBean;
    private PlayerView playerView;
    private TextView tvUserName;
    private TextView tvTitle;
    private TextView tvMusic;
    private ImageView ivAvatar;
    private TextView tvPraise;
    private TextView tvComment;
    private TextView tvShare;
    private ImageView ivMusic;

    public VideoItemView(@NonNull Context context) {
        super(context, null);
    }

    public VideoItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_item_main_video, this, true);
        playerView = findViewById(R.id.player_view);
        tvUserName = findViewById(R.id.tv_user_name);
        tvTitle = findViewById(R.id.tv_title);
        tvMusic = findViewById(R.id.tv_music);
        ivAvatar = findViewById(R.id.iv_avatar);
        tvPraise = findViewById(R.id.tv_praise);
        tvComment = findViewById(R.id.tv_comment);
        tvShare = findViewById(R.id.tv_share);
        ivMusic = findViewById(R.id.iv_music);
    }


    private void initView() {
        tvUserName.setText(mUserBean.getmName());
        tvTitle.setText(mVideoBean.getmTitle());
        tvMusic.setText(mVideoBean.getmMusicName());
        tvComment.setText(mVideoBean.getmComment() + "");
        tvPraise.setText(mVideoBean.getmPraise() + "");
        tvShare.setText(mVideoBean.getmShare() + "");
    }

    public void setVideoBean(VideoBean mVideoBean) {
        this.mVideoBean = mVideoBean;
        if (mVideoBean == null || mVideoBean.getmUserBean() == null) {
            return;
        }
        this.mUserBean = mVideoBean.getmUserBean();
        mMediaController = new MediaController();
        mMediaController.setVideoView(playerView, mVideoBean.getmLink());
        initView();
    }

    public MediaController getMediaController() {
        if (mMediaController == null) {
            mMediaController = new MediaController();
            mMediaController.setVideoView(playerView, mVideoBean.getmLink());
        }

        return mMediaController;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mVideoBean != null && mMediaController.getStats() == 1 && mMediaController != null) {
            mMediaController.setVideoView(playerView, mVideoBean.getmLink());
        }
    }

}
