package com.yang.linus.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yang.linus.R;

import java.util.Random;

public class RecyclerViewDomeActivity extends AppCompatActivity {

    private RecyclerView mRvView;
    private MyAdapter myAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_dome);
        mRvView = (RecyclerView) findViewById(R.id.rv_view);
        myAdapter = new MyAdapter(this);
        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvView.setAdapter(myAdapter);
        mRvView.setLayoutManager(mManager);
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

}
