package com.yichuizi.tiktik.activity;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.baselibrary.basecomponent.BaseActivity;
import com.yichuizi.tiktik.fragment.FastVideoFragment;

import java.util.ArrayList;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 18:17
 * 描述：
 */
public class FastHandActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> mFragmentContainter = new ArrayList<>();
    private String[] mTitles = new String[]{"关注", "发现", "同城"};


    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public int createLayout() {
        return R.layout.activity_fasthand;
    }

    @Override
    public void initView(View view) {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        for (int i = 0; i < mTitles.length; i++) {
            mFragmentContainter.add(new FastVideoFragment());
            tabLayout.addTab(tabLayout.newTab());
        }

        tabLayout.setupWithViewPager(viewPager, false);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragmentContainter.get(i);
            }

            @Override
            public int getCount() {
                return mFragmentContainter.size();
            }
        });

        for (int i = 0; i < mTitles.length; i++) {
            tabLayout.getTabAt(i).setText(mTitles[i]);
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }
}
