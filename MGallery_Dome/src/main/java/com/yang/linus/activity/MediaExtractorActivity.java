package com.yang.linus.activity;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yang.linus.R;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaExtractorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_extractor_play);
        findViewById(R.id.bt_extractor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MediaExtractor extractor = new MediaExtractor();

        try {
            extractor.setDataSource("/storage/emulated/0/sintel.mp4");
            int numTracks = extractor.getTrackCount(); // 获取音轨总数，　视频和音乐
            for(int i = 0; i < numTracks; i++) {
                MediaFormat format = extractor.getTrackFormat(i);
                String mine = format.getString(MediaFormat.KEY_MIME);
                if(mine.equals("video/avc")) {
                    extractor.selectTrack(i); //选择一个轨道输出
                }
            }

            ByteBuffer inputBuffer = ByteBuffer.allocate(500 * 1024);
            while(extractor.readSampleData(inputBuffer, 0) >= 0) {
                int trackIndex = extractor.getSampleTrackIndex(); //获得当前选择的轨道
                long presentationTimeUs = extractor.getSampleTime(); //返回当前样本展示的微妙，如果没有则返回-1
                int flags = extractor.getSampleFlags();
                System.out.println(trackIndex+"===="+presentationTimeUs +"===="+flags);
                extractor.advance();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("出来了　");


    }
}
