package com.yichuizi.tiktik.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.activity.PhotoDetailActivity;
import com.yichuizi.tiktik.adapter.FhMainVideoAdapter;
import com.yichuizi.tiktik.baselibrary.basecomponent.BaseFragment;
import com.yichuizi.tiktik.bean.UserBean;
import com.yichuizi.tiktik.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/4 13:47
 * 描述：手字页单独的小家伙
 */
public class FastVideoFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private FhMainVideoAdapter mFhMainVideoAdapter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    @Override
    public int createLayout() {
        return R.layout.fragment_fasthand;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mFhMainVideoAdapter = new FhMainVideoAdapter();
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mFhMainVideoAdapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        /**
         * 又要去造假数据啦啦啦
         */
        initData();
        mFhMainVideoAdapter.setOnItemClickListenner(new FhMainVideoAdapter.OnItemClickListenner() {
            @Override
            public void onItemClick(VideoBean videoBean, View view, int postion) {
                Intent intent = new Intent(getActivity(), PhotoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("videobean", videoBean);
                intent.putExtras(bundle);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "share").toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    private void initData() {
        Observable.create(new ObservableOnSubscribe<List<VideoBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<VideoBean>> e) throws Exception {
                List<VideoBean> mData = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    VideoBean videoBean = new VideoBean();
                    videoBean.setmVideoId(i);
                    videoBean.setmLink("http://vfx.mtime.cn/Video/2018/12/28/mp4/181228101731028203.mp4");
                    videoBean.setmTitle("王者少年 中文版预告片");
                    videoBean.setmMusicName("奇幻片《王者少年》");
                    videoBean.setmCoverUrl("data:image/jpeg;base64,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");
                    videoBean.setmPraise(1500);
                    videoBean.setmComment(230);
                    videoBean.setmShare(10);
                    UserBean userBean = new UserBean("一锤子", "http://img5.mtime.cn/mg/2018/12/28/101550.36961712_120X90X4.jpg");
                    videoBean.setmUserBean(userBean);
                    mData.add(videoBean);
                }
                e.onNext(mData);
            }
        }).observeOn(Schedulers.newThread()).subscribe(new Consumer<List<VideoBean>>() {
            @Override
            public void accept(List<VideoBean> videoBeans) throws Exception {
                mFhMainVideoAdapter.addDataAndClear(videoBeans);
            }
        });


    }
}
