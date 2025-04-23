package com.example.multimedia;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = findViewById(R.id.videoView);
        String videoUri = getIntent().getStringExtra("videoUri");

        videoView.setVideoURI(Uri.parse(videoUri));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }
}