package com.cuong.project_q;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import java.util.Random;



public class PlayGameActivity extends AppCompatActivity {
    TextView score,tvtime, numberQuestion;
    String a,b,c,d,question;
    FlagQuestionActivity nameFlag=null;
    NameQuestionActivity picFlag=null;
    int numberOfQuestion =15;
    int[] contentQuetions;
    String[] levelQuestion;
    int scores=0;
    int count;
    String stringLevel;
    int level;
    MediaPlayer wrongSoundEndTime;
    MediaPlayer endsound;
    CountDownTimer cTimer = null;
    MediaPlayer music = new MediaPlayer();
    MediaPlayer answerMusic = new MediaPlayer();
    Button volum;
    boolean change=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_question);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
        volum=(Button)findViewById(R.id.volum2);
        endsound = MediaPlayer.create(this, R.raw.endsound);
        music = MediaPlayer.create(this, R.raw.nhacnen1);
        music.setLooping(true);
        music.start();
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
        score = findViewById(R.id.TotalScore);
        tvtime= findViewById(R.id.TxtTime);
        numberQuestion = findViewById(R.id.TxtNumQuestion);
        wrongSoundEndTime=MediaPlayer.create(this,R.raw.wrong);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        stringLevel = packageFromCaller.getString("level");
        if(stringLevel.compareTo("country_easy")==0)
            level = 0;
        else
            if(stringLevel.compareTo("country_normal")==0)
                level = 1;
            else
                level = 2;
        Resources res=getResources();
        levelQuestion =res.getStringArray(getResources().getIdentifier(packageFromCaller.getString("level"),
                "array","com.cuong.project_q"));
        disPhlayQuestion(levelQuestion);
        addFragment();
    }
    void startTimer() {
        cTimer = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvtime.setText(String.valueOf( millisUntilFinished / 1000));
            }
            public void onFinish() {
                removedFragment(null);
                wrongSoundEndTime.start();
            }
        };
        cTimer.start();
    }

    public void addFragment()
    {
        startTimer();
        Question();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Random random=new Random();
        int typeQuestion = random.nextInt(2);
        switch (typeQuestion)
        {
            case 0: {
                nameFlag =new FlagQuestionActivity();
                dataTranmission(nameFlag);
                fragmentTransaction.add(R.id.FrameContent,nameFlag,"fragment");
                fragmentTransaction.commit();
                break;}
            case 1: {
                picFlag =new NameQuestionActivity();
                dataTranmission(picFlag);
                fragmentTransaction.add(R.id.FrameContent,picFlag,"fragment");
                fragmentTransaction.commit();
                break;}
        }
        numberQuestion.setText(String.valueOf(numberOfQuestion)+"/15");
        numberOfQuestion--;




    }
    public void removedFragment(View view)
    {
        cancelTimer();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Fragment fragment=getSupportFragmentManager().findFragmentByTag("fragment");
        if(nameFlag==null)
        { if(view ==null){}
        else if(picFlag.checkNameQuestion(view))
        {
            view.setBackgroundColor(Color.rgb(51, 255, 51));
            answerMusic = MediaPlayer.create(this, R.raw.correctsound);
            answerMusic.start();
            scores++;
            score.setText(String.valueOf(scores));
        }
        else if(!picFlag.checkNameQuestion(view)) {
            view.setBackgroundColor(Color.rgb(255, 51, 51));
            answerMusic = MediaPlayer.create(this, R.raw.wrong);
            answerMusic.start();
        }
            picFlag=null;

        }
        else if(picFlag==null)
        { if(view ==null){}
        else if(nameFlag.CheckFlagAnswer(view))
        {
            view.setBackgroundColor(Color.rgb(51, 255, 51));
            answerMusic = MediaPlayer.create(this, R.raw.correctsound);
            answerMusic.start();
            scores++;
            score.setText(String.valueOf(scores));
        }
        else if(!nameFlag.CheckFlagAnswer(view))
        {
            view.setBackgroundColor(Color.rgb(255, 51, 51));
            answerMusic = MediaPlayer.create(this, R.raw.wrong);
            answerMusic.start();
        }
            nameFlag=null;
        }

        if(numberOfQuestion !=0) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
            addFragment();
        }
        else {
            openResultDialog();
            count = DatabaseHandlerActivity.getInstance(PlayGameActivity.this).getNumberOfPoint(level);
            DatabaseHandlerActivity.getInstance(PlayGameActivity.this).addPoint(count, scores, level);
//            if (DatabaseHandlerActivity.getInstance(PlayGameActivity.this).addPoint(count, scores, level)) {
//                Toast.makeText(PlayGameActivity.this, level + "Them thanh cong", Toast.LENGTH_SHORT).show();
//            }
        }


    }

    public void dataTranmission(Fragment fragment)
    {
        Bundle bundle=new Bundle();
        bundle.putString("a",a);
        bundle.putString("b",b);
        bundle.putString("c",c);
        bundle.putString("d",d);
        bundle.putString("question",question);
        fragment.setArguments(bundle);
    }
    public void Question()
    {
        question= levelQuestion[contentQuetions[15- numberOfQuestion]];
        int stringLength = levelQuestion.length;// độ đài chuối của cấp độ

        int[] question =new int[3];
        int[] chooseQuestion=new int[stringLength]; //độ dài chuối của cấp độ
        for(int i=0;i<stringLength;i++)//độ dài chuối của cấp độ
        {
            chooseQuestion[i]=i;
        }
        chooseQuestion[contentQuetions[15- numberOfQuestion]]=chooseQuestion[stringLength-1];

        stringLength--;

        int i=0;
        do {
            int rand=new Random().nextInt(chooseQuestion.length);
            question[i]=chooseQuestion[rand];
            chooseQuestion[rand]=chooseQuestion[chooseQuestion.length-1];
            stringLength--;
            i++;
        }while (i<3);



        addQuestion(levelQuestion[question[0]], levelQuestion[question[1]], levelQuestion[question[2]]);
    }
    public void addQuestion(String ansa, String ansb, String ansc)
    {
        int result =new Random().nextInt(4);
        switch (result)
        {
            case 0:{
                a=question;
                b=ansa;
                c=ansb;
                d=ansc;
                break;}
            case 1:{
                a=ansa;
                b=question;
                c=ansb;
                d=ansc;
                break;}
            case 2:{ a=ansa;
                b=ansb;
                c=question;
                d=ansc;
                break;}
            case 3:{ a=ansa;
                b=ansb;
                c=ansc;
                d=question;
                break;}
        }
    }

    public void disPhlayQuestion(String[] question)
    {
        int stringLength=question.length;
        contentQuetions=new int[16];
        int rand = new Random().nextInt(stringLength-1);
        contentQuetions[0]=rand;
        for(int i=1;i<15; i++)
        {
            if(rand==stringLength-1)
                rand=0;
            rand++;
            contentQuetions[i]=rand;

        }
    }
    void cancelTimer() {
        if(cTimer!=null) {
            cTimer.cancel();
        }
    }
    public void openResultDialog()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        endsound.start();
        ResultActivity old_fragment = ResultActivity.newInstance(scores);
        transaction.add(old_fragment,"dialog");
        transaction.commit();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cTimer.cancel();
    }

}