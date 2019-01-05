package com.yichuizi.tiktik.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.ui.PlayerView;
import com.yichuizi.tiktik.R;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/4 22:17
 * 描述：
 */
public class RecyclerViewHeadView extends FrameLayout {
    //private ViewDragHelper mViewDragHelper;
    public PlayerView mPlayerView;

    public RecyclerViewHeadView(@NonNull Context context) {
        super(context);
        init();
    }

    public RecyclerViewHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPlayerView = (PlayerView) LayoutInflater.from(getContext()).inflate(R.layout.layout_player,this,false);
        addView(mPlayerView);

//        mViewDragHelper = ViewDragHelper.create(this, 0.5f, new ViewDragHelper.Callback() {
//            @Override
//            public boolean tryCaptureView(View child, int pointerId) {
//                if (child == mPlayerView) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//
//            @Override
//            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                return left;
//            }
//
//            @Override
//            public int clampViewPositionVertical(View child, int top, int dy) {
//                return top;
//            }
//
//
//            @Override
//            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                invalidate();
//
//            }
//
//
//            @Override
//            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
//
//                changedView.setScaleX((getHeight() - top) / (float) getHeight());
//                changedView.setScaleY((getHeight() - top) / (float) getHeight());
//                super.onViewPositionChanged(changedView, left, top, dx, dy);
//            }
//        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        try {
//            mViewDragHelper.processTouchEvent(event);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
}
