package com.yichuizi.tiktik.media;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 14:08
 * 描述：
 */
public class MediaController implements Player.EventListener {
    private SimpleExoPlayer mSimpleExoPlayer;

    private OnMediaControllerListener mOnMediaControllerListener;

    private Context context;
    private String playUrl;

    public void setVideoView(PlayerView simpleExoPlayerView, String videopath) {
        context = simpleExoPlayerView.getContext();
        this.playUrl = videopath;
        RenderersFactory rendersFactory = new DefaultRenderersFactory(context, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(rendersFactory, mTrackSelector, new DefaultLoadControl());
        simpleExoPlayerView.setPlayer(mSimpleExoPlayer);

        mSimpleExoPlayer.prepare(new ExtractorMediaSource.Factory(PlayerCacheUtil.getDefault(context).getDataSourceFactory()).createMediaSource(Uri.parse(videopath)));
        mSimpleExoPlayer.addListener(this);

    }


    /**
     * 开始播放
     */
    public void startPlay() {
        mSimpleExoPlayer.setPlayWhenReady(true);
    }


    /**
     * 暂停播放
     */
    public void pausePlay() {
        mSimpleExoPlayer.setPlayWhenReady(false);
    }

    /**
     * 设置声音大小 0-1
     *
     * @param value
     */
    public void setVolume(float value) {
        mSimpleExoPlayer.setVolume(value);
    }


    public int getStats() {
        if (mSimpleExoPlayer != null) {
            return mSimpleExoPlayer.getPlaybackState();
        }
        return -1;
    }

    /**
     * 结束播放
     */
    public void stopPlay() {
        mSimpleExoPlayer.stop();
        mSimpleExoPlayer.release();
        mSimpleExoPlayer.removeListener(this);
        mOnMediaControllerListener = null;
    }

    public void setOnMediaControllerListener(OnMediaControllerListener mOnMediaControllerListener) {
        this.mOnMediaControllerListener = mOnMediaControllerListener;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == Player.STATE_ENDED) {
            restartPlay();
        }
        if (playbackState == Player.STATE_READY) {

        }
        if (playbackState == Player.STATE_BUFFERING) {

        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    private void restartPlay() {
        mSimpleExoPlayer.setPlayWhenReady(true);
        mSimpleExoPlayer.seekTo(0L);

    }

    public interface OnMediaControllerListener {

        void onCompletion();

        void onError(int i, int i1);

        void onInfo(int i, int i1);
    }
}

