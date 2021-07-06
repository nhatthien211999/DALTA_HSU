package com.cuong.project_q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends Activity {
    Button easy, hard, normal,back;
    int point=0;
    String s=" ";

    int level;
    ListView listView;
     ArrayAdapter adapter;
    ArrayList<String> arrayScore;
    TextView txt_mode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
        easy =(Button)findViewById(R.id.BtScoreEasy);
        normal =(Button)findViewById(R.id.BtScoreNormal);
        hard =(Button)findViewById(R.id.BtScoreHard);
        listView=(ListView)findViewById(R.id.list_score);
        back=(Button)findViewById(R.id.BtBackLevel);
        txt_mode = (TextView)findViewById(R.id.Txt_mode);

        arrayScore = new ArrayList<>();
        adapter =new ArrayAdapter(HighScoreActivity.this,android.R.layout.simple_list_item_1,arrayScore);
        listView.setAdapter(adapter);
        //Toast.makeText(HighScoreActivity.this, String.valueOf(slde) + "+"+ String.valueOf(slkho) + "+" + String.valueOf(slcuc), Toast.LENGTH_SHORT).show();

//        level =??????; Gán level vô... 0 là dễ , 1 là bình thường , 2 là khó
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_mode.setText("Mode: Eazy");
                level = 0;
                if(DatabaseHandlerActivity.getInstance(HighScoreActivity.this).getNumberOfPoint(level) !=0)
                {
                    List<Integer> arrPoint = DatabaseHandlerActivity.getInstance(HighScoreActivity.this).getTopPoint(level);

                    arrayScore.clear();
                    for(int i=0; i<arrPoint.size(); i++) {
                        arrayScore.add(String.valueOf(arrPoint.get(i)));

                    }
                    adapter.notifyDataSetChanged();


                }
                else
                {
                    arrayScore.clear();
                    adapter.notifyDataSetChanged();

                }
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                txt_mode.setText("Mode: Normal");
                level = 1;

                if(DatabaseHandlerActivity.getInstance(HighScoreActivity.this).getNumberOfPoint(level) !=0)
                {
                   // Toast.makeText(HighScoreActivity.this, "vo dc", Toast.LENGTH_SHORT).show();

                    List<Integer> arrPoint = DatabaseHandlerActivity.getInstance(HighScoreActivity.this).getTopPoint(level);

                    arrayScore.clear();
                    for(int i=0; i<arrPoint.size(); i++) {
                        arrayScore.add(String.valueOf(arrPoint.get(i)));

                    }
                    adapter.notifyDataSetChanged();


                }
                else
                {
                    arrayScore.clear();
                    adapter.notifyDataSetChanged();

                }
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_mode.setText("Mode: Hard");
                level = 2;
                if(DatabaseHandlerActivity.getInstance(HighScoreActivity.this).getNumberOfPoint(level) !=0)
                {
                    List<Integer> arrPoint = DatabaseHandlerActivity.getInstance(HighScoreActivity.this).getTopPoint(level);

                    arrayScore.clear();
                    for(int i=0; i<arrPoint.size(); i++) {
                        arrayScore.add(String.valueOf(arrPoint.get(i)));

                    }
                    adapter.notifyDataSetChanged();

                }
                else
                {
                    arrayScore.clear();
                    adapter.notifyDataSetChanged();

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
