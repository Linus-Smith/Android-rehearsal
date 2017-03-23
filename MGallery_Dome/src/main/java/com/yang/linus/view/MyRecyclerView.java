package com.yang.linus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Linus on 2017/3/22.
 */

public class MyRecyclerView extends RecyclerView {

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
