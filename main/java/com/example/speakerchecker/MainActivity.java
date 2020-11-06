package com.example.speakerchecker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Uri uri;
    int checkSpeaker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setVolume(1,1);
        uri = null;
        checkSpeaker = 0; // 0= both, 1=left, 2=right
    }

    public void selectMusic(View view) {

        mediaPlayer.reset();
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode ==RESULT_OK){


            uri = data.getData();

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(this, uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void play(View view) {

        mediaPlayer.start();
    }



    public void speakerLeft(View view) {
        mediaPlayer.setVolume(1,0);
    }

    public void speakerRight(View view){
        mediaPlayer.setVolume(0,1);
    }

    public void speakerBoth(View view){
        mediaPlayer.setVolume(1,1);
    }
}
