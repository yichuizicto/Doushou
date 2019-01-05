package com.yichuizi.tiktik.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.yichuizi.tiktik.R;


/**
 * 作者：一锤子打扫卫生的 on 2019/1/5 18:50
 * 描述：
 */
public class DetailFrameLayout extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private RecyclerView mRecyclerView;
    private FrameLayout mFrameLayout;
    private int mLastYIntercept, mLastXIntercept;
    private boolean intercepted;
    private OnDetailFrameLayoutListener mOnDetailFrameLayoutListener;

    public interface OnDetailFrameLayoutListener {
        void onFinish();//关闭页面

        void onJumpPerson();//跳转到个人页
    }

    public DetailFrameLayout(@NonNull Context context) {
        super(context, null);
    }

    public DetailFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnDetailFrameLayoutListener(OnDetailFrameLayoutListener mOnDetailFrameLayoutListener) {
        this.mOnDetailFrameLayoutListener = mOnDetailFrameLayoutListener;
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 0.5f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (Math.abs(releasedChild.getX()) > 500 || Math.abs(releasedChild.getY()) > 500) {
                    if (mOnDetailFrameLayoutListener != null) {
                        mOnDetailFrameLayoutListener.onFinish();
                    }
                } else {
                    mViewDragHelper.smoothSlideViewTo(releasedChild, 0, 0);
                    invalidate();
                }
            }


            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                changedView.setScaleX((getHeight() - top) / (float) getHeight());
                changedView.setScaleY((getHeight() - top) / (float) getHeight());
                mFrameLayout.setAlpha((getHeight() - top) / (float) getHeight());
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRecyclerView = findViewById(R.id.recyclerview);
        mFrameLayout = findViewById(R.id.fl_second);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mViewDragHelper.processTouchEvent(ev);
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;

                if (Math.abs(deltaX) < Math.abs(deltaY) && deltaY > 10) {
                    intercepted = true;
                } else if (!intercepted) {
                    intercepted = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        if (intercepted && !mRecyclerView.canScrollVertically(-1)) {
            return true;
        } else {
            return false;

        }

    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mViewDragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
