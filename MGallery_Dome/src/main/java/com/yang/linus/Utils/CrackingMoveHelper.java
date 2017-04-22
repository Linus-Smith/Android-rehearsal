package com.yang.linus.Utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Linus on 2017/4/14.
 */

public class CrackingMoveHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private MyOnScrollListener mMyOnScrollListener;
    private CrackingLayoutManager mLayoutManager;
    private boolean isInitValue = false;
    private int mItemWidth = 90;
    private float mCententX;

    public CrackingMoveHelper() {
        mMyOnScrollListener = new MyOnScrollListener();
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        if(mRecyclerView == recyclerView) {
            return; // nothing to do;
        }

        if(mRecyclerView != null) {
            destroyCallbacks();
        }
        mRecyclerView = recyclerView;
        setupCallbacks();
        new Thread(){
            @Override
            public void run() {
                super.run();
                mRecyclerView.postInvalidate();
            }
        }.start();
    }



    private void setupCallbacks() {
        mRecyclerView.addItemDecoration(this);
        mRecyclerView.addOnItemTouchListener(this);
        mRecyclerView.addOnScrollListener(mMyOnScrollListener);
        mLayoutManager = new CrackingLayoutManager(mRecyclerView.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void destroyCallbacks() {
        mRecyclerView.removeItemDecoration(this);
        mRecyclerView.removeOnItemTouchListener(this);
        mRecyclerView.removeOnScrollListener(mMyOnScrollListener);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        mRecyclerView.requestLayout();
        mRecyclerView.invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    class MyOnScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

    class MyLayoutManager extends LinearLayoutManager {

        public MyLayoutManager(Context context) {
            super(context);
        }

        public MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public MyLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }


        @Override
        public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
            return super.scrollHorizontallyBy(dx, recycler, state);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            System.out.println(recycler.getScrapList());
            super.onLayoutChildren(recycler, state);
        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            super.onMeasure(recycler, state, widthSpec, heightSpec);

        }
    }
}
