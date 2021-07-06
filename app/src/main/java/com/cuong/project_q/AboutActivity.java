package com.cuong.project_q;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AboutActivity extends AppCompatActivity {
    TextView hd;
    Button back;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        back=(Button)findViewById(R.id.BtBackAbout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
