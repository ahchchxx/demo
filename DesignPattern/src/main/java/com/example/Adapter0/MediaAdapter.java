package com.example.Adapter0;

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer player;

    public MediaAdapter(String audioType) {
        if (audioType.equals("vlc")) {
            player = new VlcPlayer();
        } else if (audioType.equals("mp4")) {
            player = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equals("vlc")) {
            player.playVlc(fileName);
        } else if (audioType.equals("mp4")) {
            player.playMp4(fileName);
        }
    }
}
