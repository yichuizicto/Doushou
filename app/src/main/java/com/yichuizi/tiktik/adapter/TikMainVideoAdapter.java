package com.yichuizi.tiktik.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.bean.VideoBean;
import com.yichuizi.tiktik.media.VideoPlayerManager;
import com.yichuizi.tiktik.view.VideoItemView;

import java.util.List;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 10:14
 * 描述：
 */
public class TikMainVideoAdapter extends RecyclerView.Adapter<TikMainVideoAdapter.MainVideoHolder> {

    private int position = 0;
    private List<VideoBean> mData;

    public void addDataAndClear(List<VideoBean> mData) {
        if (this.mData != null) {
            this.mData.clear();
            this.mData.addAll(mData);
        } else {
            this.mData = mData;
        }
        //此处该有一个优化
        notifyDataSetChanged();
    }

    public void addData(List<VideoBean> mData) {
        if (this.mData != null) {
            this.mData.addAll(mData);
        } else {
            this.mData = mData;
        }
        //此处该有一个优化
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainVideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainVideoHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.layout_item_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainVideoHolder mainVideoHolder, int i) {
        VideoBean mVideoBean = mData.get(i);
        if (mVideoBean != null) {
            mainVideoHolder.mVideoItemView.setVideoBean(mVideoBean);
        }
        if (i == position) {
            VideoPlayerManager.getInstance().setCurrentVideoPlayer(mainVideoHolder.mVideoItemView.getMediaController());
            VideoPlayerManager.getInstance().startPlay();
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    class MainVideoHolder extends RecyclerView.ViewHolder {
        public VideoItemView mVideoItemView;

        public MainVideoHolder(@NonNull View itemView) {
            super(itemView);
            mVideoItemView = itemView.findViewById(R.id.video_item_view);
        }
    }
}
