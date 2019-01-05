package com.yichuizi.tiktik.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.adapter.TikMainVideoAdapter;
import com.yichuizi.tiktik.baselibrary.basecomponent.BaseFragment;
import com.yichuizi.tiktik.bean.UserBean;
import com.yichuizi.tiktik.bean.VideoBean;
import com.yichuizi.tiktik.media.VideoPlayerManager;
import com.yichuizi.tiktik.view.VideoItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/2 22:39
 * 描述：
 */
public class TikMainFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TikMainVideoAdapter mMainVideoAdapter;
    private SnapHelper mSnapHelper = new PagerSnapHelper();

    @Override
    public int createLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMainVideoAdapter = new TikMainVideoAdapter();
        mRecyclerView.setAdapter(mMainVideoAdapter);
        mSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void doBusiness(Context mContext) {
        /**
         * 此时此刻是不是应该假模假样的去请求下网络？嗯去了
         */
        initData();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /**
                 * newState 1:手指正在滑动 2:手指抛了下 0:停止滑动
                 */
                if (newState == 0) {
                    int i = mLinearLayoutManager.findFirstVisibleItemPosition();
                    VideoItemView videoItemView = (VideoItemView) mLinearLayoutManager.findViewByPosition(i);
                    VideoPlayerManager.getInstance().setCurrentVideoPlayer(videoItemView.getMediaController());
                    VideoPlayerManager.getInstance().startPlay();
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (VideoPlayerManager.getInstance().getMediaController() != null) {
            VideoPlayerManager.getInstance().getMediaController().startPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (VideoPlayerManager.getInstance().getMediaController() != null) {
            VideoPlayerManager.getInstance().getMediaController().pausePlay();
        }
    }

    private void initData() {
        List<VideoBean> mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            VideoBean videoBean = new VideoBean();
            videoBean.setmVideoId(i);
            videoBean.setmLink("http://vfx.mtime.cn/Video/2018/12/28/mp4/181228101731028203.mp4");
            videoBean.setmTitle("王者少年 中文版预告片");
            videoBean.setmMusicName("奇幻片《王者少年》");
            videoBean.setmPraise(1500);
            videoBean.setmComment(230);
            videoBean.setmShare(10);
            UserBean userBean = new UserBean("一锤子", "http://img5.mtime.cn/mg/2018/12/28/101550.36961712_120X90X4.jpg");
            videoBean.setmUserBean(userBean);
            mData.add(videoBean);
        }
        mMainVideoAdapter.addDataAndClear(mData);

    }
}
