package com.yichuizi.tiktik.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.baselibrary.basecomponent.BaseActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 18:17
 * 描述：
 */
public class SplashActivity extends BaseActivity {

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int createLayout() {
        return R.layout.activity_index;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        mCompositeDisposable.add(Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Random random = new Random();
                int i = random.nextInt(10);
                if (i > 5) {
                    startActivity(new Intent(SplashActivity.this, TikTikActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, FastHandActivity.class));
                }
                finish();
            }
        }));
    }

    @Override
    public void widgetClick(View v) {
        mCompositeDisposable.clear();
        switch (v.getId()) {
            case R.id.tv_fasthand:
                startActivity(new Intent(this, FastHandActivity.class));
                break;
            case R.id.tv_tiktik:
                startActivity(new Intent(this, TikTikActivity.class));
                break;
        }
        finish();
    }
}
