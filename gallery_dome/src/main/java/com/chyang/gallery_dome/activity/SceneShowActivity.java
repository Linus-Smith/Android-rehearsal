package com.chyang.gallery_dome.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.chyang.gallery_dome.R;
import com.chyang.gallery_dome.ui.BitmapSceneView;
import com.chyang.gallery_dome.ui.SceneShowView;

import java.util.ArrayList;
import java.util.List;

public class SceneShowActivity extends AppCompatActivity implements BitmapSceneView.OnCompletionListener, View.OnClickListener{

    private static final long SLIDESHOW_DELAY = 3000; // 3 seconds

    private static int index = 1;
    int  id ;
    private BitmapSceneView mSceneShowView;
    private Button btStart;
    private boolean isPlay = false;
    private Bitmap mCurrentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_switch);
        mSceneShowView = (BitmapSceneView) findViewById(R.id.sv);
        mSceneShowView.setOnCompletionListener(this);
        btStart = (Button) findViewById(R.id.bt_start);
        findViewById(R.id.bt_stop).setOnClickListener(this);
        btStart.setOnClickListener(this);
        int height  = getResources().getDimensionPixelOffset(R.dimen.canvas_height);
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        int height1 = wm1.getDefaultDisplay().getHeight();
        mSceneShowView.setCanvasWidth(width1);
        mSceneShowView.setCanvasHeight(height);
        startBitmap();

    }

    @Override
    public void onCompletion() {
        Log.d("chyang","onCompletion");
            startBitmap();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.bt_start) {
            startBitmap();
        } else {
            mSceneShowView.setShowBitmap(mCurrentBitmap);
        }
    }


    private void startBitmap() {
        if(index < 10) {
            id = getResources().getIdentifier("imag00" + index, "mipmap", getPackageName());
        } else {
            id = getResources().getIdentifier("imag0" + index, "mipmap", getPackageName());
        }

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), id);
         mBitmap = resizeImage(mBitmap, 1080, 900 );
        if(mBitmap != null) {

           mSceneShowView.showNextScene(mBitmap, 6000,0, 0);
            mCurrentBitmap = mBitmap;

        }
        index++;
        if(index == 19) {
            index = 1;
        }
    }


    public Bitmap resizeImage(Bitmap bitmap, int w, int h) {

        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;

    }
}
