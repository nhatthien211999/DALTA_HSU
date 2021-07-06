package com.cuong.project_q;

        import android.app.Activity;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.annotation.Nullable;

public class LevelActivity extends Activity {
    Button back, easy, normal, hard;
    Animation rotateAnimation;
    TextView txtChoose;
    MediaPlayer music;
    Button volum;
    boolean change=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
        easy =(Button)findViewById(R.id.BtLevelEasy);
        normal =(Button)findViewById(R.id.BtLevelNormal);
        hard =(Button)findViewById(R.id.BtLevelHard);
        back=(Button)findViewById(R.id.BtBackLevel);
        volum=(Button) findViewById(R.id.volum1);
        music = MediaPlayer.create(this, R.raw.nhacnen2);
        music.setLooping(true);
        music.start();
        back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LevelActivity.this,MainActivity.class);
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
        txtChoose=(TextView)findViewById(R.id.TxtChooseLevel);

        rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        txtChoose.setAnimation(rotateAnimation);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelActivity.this, PlayGameActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("level","country_easy");
                intent.putExtra("MyPackage",bundle);
                startActivity(intent);
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelActivity.this, PlayGameActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("level","country_normal");
                intent.putExtra("MyPackage",bundle);
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelActivity.this, PlayGameActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("level","country_hard");
                intent.putExtra("MyPackage",bundle);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        if(music.isPlaying())
            music.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        music.start();
        super.onResume();
    }
}