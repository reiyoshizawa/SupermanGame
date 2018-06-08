package com.example.yoshizawarei.supermangame;

import android.content.Context;
import android.media.MediaPlayer;

public class BgmPlayer {

    private MediaPlayer mediaPlayer;

    public BgmPlayer(Context context) {
        this.mediaPlayer = MediaPlayer.create(context, R.raw.bgm);

        this.mediaPlayer.setLooping(true);

        this.mediaPlayer.setVolume(1.0f, 1.0f);

//        this.mediaPlayer.attachAuxEffect(1);
    }

    public void start() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.prepareAsync();
        }
    }
}
