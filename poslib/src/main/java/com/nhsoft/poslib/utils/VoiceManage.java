package com.nhsoft.poslib.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class VoiceManage {
    public static VoiceManage mVoiceManage;
    private static MediaPlayer mMediaPlayer;
    private VoiceManage(){}
    public static VoiceManage getInstance(){
        if (mVoiceManage==null){
            synchronized (VoiceManage.class){
                if (mVoiceManage==null){
                    mVoiceManage=new VoiceManage();
                }
            }
        }
        return mVoiceManage;
    }

    public void createMediaPlayer(Context context, int mp3Name){
        mMediaPlayer=MediaPlayer.create(context,mp3Name);
        mMediaPlayer.start();
    }

    public void stopMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
