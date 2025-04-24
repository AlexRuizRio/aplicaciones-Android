package com.example.multimedia;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<ItemData> itemList;
    private MediaPlayer mediaPlayer;

    public ImageAdapter(Context context, List<ItemData> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        ItemData item = itemList.get(i);

        ImageButton image = view.findViewById(R.id.imageButton4);
        ImageButton btnPlay = view.findViewById(R.id.imageButton5);
        ImageButton btnStop = view.findViewById(R.id.imageButton6);

        image.setImageResource(item.imageResId);

        // Al pulsar la imagen, abre el video
        image.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoActivity.class);
            String videoUri = "android.resource://" + context.getPackageName() + "/" + item.videoResId;
            intent.putExtra("videoUri", videoUri);
            context.startActivity(intent);
        });

        // Reproducir audio
        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(context, item.audioResId);
            mediaPlayer.start();
        });

        // Parar audio
        btnStop.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
        return view;
    }
}

