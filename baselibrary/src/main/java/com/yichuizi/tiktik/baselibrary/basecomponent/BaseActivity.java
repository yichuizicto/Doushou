package com.yichuizi.tiktik.baselibrary.basecomponent;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/2 22:39
 * 描述：
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;
    private String APP_NAME;
    protected final String TAG = this.getClass().getSimpleName();

    private boolean isRequestedOrientation = true;

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDebug = BaseApplication.isDebug;
        APP_NAME = BaseApplication.APP_NAME;
        $Log(TAG + "-->onCreate()");
        try {
            Bundle bundle = getIntent().getExtras();
            initParms(bundle);
            mContextView = LayoutInflater.from(this).inflate(createLayout(), null);

            setContentView(mContextView);
            if (isRequestedOrientation) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }


            initView(mContextView);
            doBusiness(this);
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [初始化Bundle参数]
     *
     * @param parms
     */
    public abstract void initParms(Bundle parms);

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int createLayout();

    /**
     * [重写： 1.是否沉浸状态栏 2.是否全屏 3.是否禁止旋转屏幕]
     */
    // public abstract void setActivityPre();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);
    }


    public void setRequestedOrientation(boolean requestedOrientation) {
        isRequestedOrientation = requestedOrientation;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }


    @Override
    protected void onResume() {
        super.onResume();
        $Log(TAG + "--->onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        $Log(TAG + "--->onDestroy()");
    }

    /**
     * [日志输出]
     *
     * @param msg
     */
    protected void $Log(String msg) {
        if (isDebug) {
            Log.d(APP_NAME, msg);
        }
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }
}

