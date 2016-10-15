package com.chyang.b_bitmapfactory.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.chyang.b_bitmapfactory.R;

public class BigBitmapShow extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_bitmap_show);
        mImageView = (ImageView) findViewById(R.id.iv_show);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show1:
                setImage1();
                break;
            case R.id.show2:
                setImage2();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setImage1() {
        BitmapFactory.Options bmpOptionsFOptions = new BitmapFactory.Options();
        bmpOptionsFOptions.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.re, bmpOptionsFOptions);
        mImageView.setImageBitmap(bitmap);
    }

    private void setImage2() {
        //获取屏幕的宽高
        Display mDisplay = getWindowManager().getDefaultDisplay();
        int dw = mDisplay.getWidth();
        int dh = mDisplay.getHeight();
        Log.d("screen size:", "width:"+dw+"   height:"+dh);

        //加载图片的尺寸而不是图像本身
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.re, bmpFactoryOptions);

        int bitmapWidth = bmpFactoryOptions.outWidth;
        int bitmapHeight =bmpFactoryOptions.outHeight;
        Log.d("image Size:", "width:"+ bitmapWidth+"    height:"+bitmapHeight);
        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight);
        

    }
}
