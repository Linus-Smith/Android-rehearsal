package com.chyang.gallery_dome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.chyang.gallery_dome.R;

import java.util.List;

/**
 * Created by caihuiyang on 2016/10/15.
 */

public class GalleryAutoScrollerAdapter  extends RecyclerView.Adapter{

    private List<Integer> mList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public GalleryAutoScrollerAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          ViewHolder mViewHolder = (ViewHolder) holder;
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
