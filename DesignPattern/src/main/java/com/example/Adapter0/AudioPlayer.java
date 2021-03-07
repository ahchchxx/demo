package com.example.Adapter0;

public class AudioPlayer implements MediaPlayer {
    MediaAdapter player;

    @Override
    public void play(String audioType, String fileName) {
        // 播放 mp3 音乐文件的内置支持
        if (audioType.equals("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
        else if (audioType.equals("vlc") || audioType.equals("mp4")) {
            // player 提供了播放其他文件格式的支持
            player = new MediaAdapter(audioType);
            player.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
