package com.yichuizi.tiktik.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.baselibrary.basecomponent.BaseActivity;
import com.yichuizi.tiktik.fragment.TikMainFragment;
import com.yichuizi.tiktik.fragment.PersonFragment;
import com.yichuizi.tiktik.fragment.SearchFragment;
import com.yichuizi.tiktik.media.VideoPlayerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/2 22:39
 * 描述：
 */
public class TikTikActivity extends BaseActivity {
    private ViewPager mViewPager;
    private List<Fragment> mFragmentContainter = new ArrayList<>();

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int createLayout() {
        return R.layout.activity_tiktik;
    }

    @Override
    public void initView(View view) {
        mViewPager = findViewById(R.id.viewpager);
        TikMainFragment mMainFragment = new TikMainFragment();
        PersonFragment mPersonFragment = new PersonFragment();
        SearchFragment mSearchFragment = new SearchFragment();
        mFragmentContainter.add(mSearchFragment);
        mFragmentContainter.add(mMainFragment);
        mFragmentContainter.add(mPersonFragment);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentContainter.size();
            }

            @Override
            public android.support.v4.app.Fragment getItem(int arg0) {
                return mFragmentContainter.get(arg0);
            }
        });
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoPlayerManager.getInstance().releaseVideoPlayer();
    }
}
