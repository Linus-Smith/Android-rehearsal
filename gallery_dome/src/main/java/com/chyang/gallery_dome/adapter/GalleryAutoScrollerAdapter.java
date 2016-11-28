package com.chyang.gallery_dome.adapter;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.chyang.gallery_dome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caihuiyang on 2016/10/15.
 */

public class GalleryAutoScrollerAdapter  extends RecyclerView.Adapter{

    private List<Integer> mList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private boolean isChange = false;
    private int index = 0;
    private RecyclerView mRecyclerView;

    public GalleryAutoScrollerAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mRecyclerView = recyclerView;
    }

    public void setData(List<Integer> mData) {
        this.mList = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parent = (ViewGroup) mLayoutInflater.inflate(R.layout.item_auto_scroller, null, false);
        ViewHolder mViewHolder = new ViewHolder(parent);
        return mViewHolder;
    }

    public void onChangePosition(boolean isChange) {
        this.isChange = isChange;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
          final ViewHolder mViewHolder = (ViewHolder) holder;
        mViewHolder.iv.setBackgroundColor(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
