package com.yichuizi.tiktik.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.adapter.DetailHeadVideoAdapter;
import com.yichuizi.tiktik.baselibrary.basecomponent.BaseActivity;
import com.yichuizi.tiktik.bean.VideoBean;
import com.yichuizi.tiktik.view.DetailFrameLayout;
import com.yichuizi.tiktik.view.PlayerVideoView;

import java.util.ArrayList;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/4 11:46
 * 描述：手子页的详情
 */
public class PhotoDetailActivity extends BaseActivity {
    private VideoBean mVideoBean;
    private DetailFrameLayout mDetailFrameLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private DetailHeadVideoAdapter mDetailHeadVideoAdapter;
    private ArrayList<String> mComments = new ArrayList<>();
    private PlayerVideoView mPlayerVideoView;

    @Override
    public void initParms(Bundle parms) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            setRequestedOrientation(false);
        }
        mVideoBean = parms.getParcelable("videobean");
        if (mVideoBean == null) {
            finish();
        }
    }

    @Override
    public int createLayout() {
        return R.layout.activity_photo_detail;
    }

    @Override
    public void initView(View view) {
        mDetailFrameLayout = findViewById(R.id.rootlayout);
        mRecyclerView = findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mDetailHeadVideoAdapter = new DetailHeadVideoAdapter();
        mRecyclerView.setAdapter(mDetailHeadVideoAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < 500; i++) {
            mComments.add(String.valueOf(i));
        }
        mPlayerVideoView = new PlayerVideoView(this);

        mPlayerVideoView.setVideoBean(mVideoBean);
        mDetailHeadVideoAdapter.setHeaderView(mPlayerVideoView);
        mDetailHeadVideoAdapter.addDatas(mComments);

        mDetailFrameLayout.setOnDetailFrameLayoutListener(new DetailFrameLayout.OnDetailFrameLayoutListener() {
            @Override
            public void onFinish() {
                onBackPressed();
            }

            @Override
            public void onJumpPerson() {

            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailFrameLayout.setOnDetailFrameLayoutListener(null);
        if (mPlayerVideoView != null) {
            mPlayerVideoView.getmMediaController().stopPlay();
        }
    }


}
