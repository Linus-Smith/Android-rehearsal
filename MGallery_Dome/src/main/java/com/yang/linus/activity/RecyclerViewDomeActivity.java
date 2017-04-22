package com.yang.linus.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yang.linus.R;
import com.yang.linus.Utils.CrackingMoveHelper;

import java.util.Random;

public class RecyclerViewDomeActivity extends AppCompatActivity {

    private RecyclerView mRvView;
    private MyAdapter myAdapter;
    private LinearLayoutManager mManager;
    private CrackingMoveHelper mCrackingMoveHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_dome);
        mRvView = (RecyclerView) findViewById(R.id.rv_view);
        myAdapter = new MyAdapter(this);
        mManager = new MyLayoutManager(this);
//        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvView.setAdapter(myAdapter);
        mRvView.setLayoutManager(mManager);
        mCrackingMoveHelper = new CrackingMoveHelper();
        mCrackingMoveHelper.attachToRecyclerView(mRvView);
    }

  class MyAdapter extends RecyclerView.Adapter {

      private Context mContext;
      private Random mRandom;

      public MyAdapter(Context context) {
          this.mContext =  context;
          mRandom = new Random();
      }

      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view  =  LayoutInflater.from(mContext).inflate(R.layout.item_recy_dome, parent, false);
          MyHolder myHolder = new MyHolder(view);

          return myHolder;
      }

      @Override
      public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof  MyHolder) {
              MyHolder myHolder = (MyHolder) holder;
              //myHolder.iv.setImageResource(R.mipmap.image2);
              myHolder.iv.setBackgroundColor(getColor());


          }
      }

      public int getColor() {
          return Color.argb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
      }

      @Override
      public int getItemCount() {
          return 400;
      }

      class MyHolder extends RecyclerView.ViewHolder {

          ImageView iv;

          public MyHolder(View itemView) {
              super(itemView);
              iv = (ImageView) itemView.findViewById(R.id.ivView);
          }
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
         }
     }

    class MyImageView extends ImageView {

        public MyImageView(Context context) {
            super(context);
        }

        public MyImageView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public MyImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }
    }



}
