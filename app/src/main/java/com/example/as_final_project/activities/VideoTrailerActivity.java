package com.example.as_final_project.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.as_final_project.R;
import com.example.as_final_project.config.BasicConfig;
import com.example.as_final_project.entities.MovieStills;
import com.example.as_final_project.utils.ToastUtil;

public class VideoTrailerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_trailer);
        setActivityToolbar(R.id.a_video_trailer_toolbar, true, false);

        MovieStills movieStills = getIntent().getParcelableExtra(BasicConfig.INTENT_DATA_NAME);
        // set video
        VideoView videoView = findViewById(R.id.a_video_trailer_videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(movieStills.getTrailerUrl()));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ToastUtil.showToast(VideoTrailerActivity.this, "预告片结束啦！");
            }
        });
        videoView.start();
    }
}
