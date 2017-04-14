package com.yang.linus.Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.yang.linus.activity.RecyclerViewDomeActivity;

/**
 * Created by Linus on 2017/4/14.
 */

public class CrackingMoveHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private MyOnScrollListener mMyOnScrollListener;
    private LinearLayoutManager mLayoutManager;
    private int winWidth;
    private int winHeight;
    private int mItemWidth;
    private int mItemHeight;

    public CrackingMoveHelper() {
        mMyOnScrollListener = new MyOnScrollListener();
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView, int itemWidth, int itemHeight) {
        if(mRecyclerView == recyclerView) {
            return; // nothing to do;
        }

        if(mRecyclerView != null) {
            destroyCallbacks();
        }
        mRecyclerView = recyclerView;
        mItemWidth = itemWidth;
        mItemHeight = itemHeight;

        setupCallbacks();
    }

    //TODO linus.yang
    private void initWindwosSize() {

    }

    private void setupCallbacks() {
        mRecyclerView.addItemDecoration(this);
        mRecyclerView.addOnItemTouchListener(this);
        mRecyclerView.addOnScrollListener(mMyOnScrollListener);
        mLayoutManager = new MyLayoutManager(mRecyclerView.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void destroyCallbacks() {
        mRecyclerView.removeItemDecoration(this);
        mRecyclerView.removeOnItemTouchListener(this);
        mRecyclerView.removeOnScrollListener(mMyOnScrollListener);
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
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            super.onLayoutChildren(recycler, state);
            View view =  recycler.getViewForPosition(0);
            System.out.println(view.getWidth()+"=========="+view.getHeight());

        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }
    }
}
