package com.example.multimedia;

public class ItemData {
    int imageResId;
    int audioResId;
    int videoResId; // Usaremos int si el vídeo está en /res/raw

    public ItemData(int imageResId, int audioResId, int videoResId) {
        this.imageResId = imageResId;
        this.audioResId = audioResId;
        this.videoResId = videoResId;
    }
}