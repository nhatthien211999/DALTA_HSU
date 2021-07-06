package com.cuong.project_q;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity  {

    VideoView videoView;
    MediaPlayer mMediaplayer;
    TextView game;
    int position;
    Button play, about, highScore, exit,volum;
    MediaPlayer music = new MediaPlayer();
    Boolean change = true;
    Animation shake,blink,move;
    ImageView helloView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

        music = MediaPlayer.create(this, R.raw.truedamage);
        music.setLooping(true);
        music.start();
        shake= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        blink=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        move=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        game=(TextView)findViewById(R.id.ProText);

        game.setAnimation(shake);

        helloView=(ImageView)findViewById(R.id.hello);
        play = (Button) findViewById(R.id.BtPlay);
        about = (Button) findViewById(R.id.BtAbout);
        highScore = (Button) findViewById(R.id.BtHighScore);
        exit = (Button) findViewById(R.id.BtOut);
        volum = (Button) findViewById(R.id.button_volum);

        play.setAnimation(blink);
        helloView.setAnimation(move);

        videoView = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://"//first start with this
                + getPackageName()// retrieve package name
                + "/" + R.raw.videoapp);//video resource
        //set the new Uri to our VideoView
        videoView.setVideoURI(uri);
        videoView.start();
        //set an OnPreparedListener for our VideoView. For more infomation about VideoView,
        //check out the android docs:https://developer.android.com/reference/android/widget/VideoView.html
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mMediaplayer = mediaPlayer;

                mMediaplayer.setLooping(true);
                //seek to the current position if it has been set and play the video
                if (position != 0) {
                    mMediaplayer.seekTo(position);
                    mMediaplayer.start();
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
                music.stop();

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
                startActivity(intent);
            }
        });
        volum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(change){
                    volum.setBackgroundResource(R.drawable.stop_music);
                    music.pause();
                    change = false;
                }
                else
                {
                    volum.setBackgroundResource(R.drawable.playmusic);
                    music.start();
                    change = true;
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOut();
            }
        });
    }


    private void gameOut(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
        alert.setTitle("Notify");
        alert.setIcon(R.drawable.notify);
        alert.setMessage("Do you want to exit the game?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onBackPressed();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.show();
    }

    //
    @Override
    protected void onPause() {
        super.onPause();
        //capture the current video position and pause the video
        position = mMediaplayer.getCurrentPosition();
        videoView.start();
        if(music.isPlaying())
            music.pause();
    }
    @Override
    protected void onResume() {
        music.start();
        super.onResume();
        //restar the video when resuming the activity
        videoView.start();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //when the activity is destroyed, release   our MediaPlayer and set it to null
        mMediaplayer.release();
        mMediaplayer=null;

    }


}