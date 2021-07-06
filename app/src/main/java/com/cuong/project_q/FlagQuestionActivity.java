package com.cuong.project_q;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;


public  class FlagQuestionActivity extends Fragment {

    Button ans1,ans2,ans3,ans4;
    ImageView question;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
     {
         View view = inflater.inflate(R.layout.activity_flag_question, container, false);
         ans1=view.findViewById(R.id.Bt_Aws_1);
         ans2=view.findViewById(R.id.Bt_Aws_2);
         ans3=view.findViewById(R.id.Bt_Aws_3);
         ans4=view.findViewById(R.id.Bt_Aws_4);
         question=view.findViewById(R.id.ima_pig);
         nhapDuLieu();
         return view;
     }
    public void nhapDuLieu() {
        Bundle bundle =getArguments();
        if(bundle!=null) {
            String h=bundle.getString("question");
           int resID=getResId(h, R.drawable.class);
            question.setImageResource(resID);
            ans1.setText(bundle.getString("a"));
            ans2.setText(bundle.getString("b"));
            ans3.setText(bundle.getString("c"));
            ans4.setText(bundle.getString("d"));
        }
    }
    public static int getResId(String resName, Class<?> c)
    {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public boolean CheckFlagAnswer(View view)
    {
        Bundle bundle =getArguments();
        boolean ketqua = false;
        if(bundle!=null) {
            switch (view.getId())
            {
                case R.id.Bt_Aws_1:
                {
                   ketqua = CheckAnswer(bundle.getString("a"),bundle.getString("question"));
                    break;
                }
                case R.id.Bt_Aws_2:
                {
                    ketqua = CheckAnswer(bundle.getString("b"),bundle.getString("question"));
                    break;
                }
                case R.id.Bt_Aws_3:
                {
                    ketqua = CheckAnswer(bundle.getString("c"),bundle.getString("question"));
                    break;
                }
                case R.id.Bt_Aws_4:
                {
                    ketqua = CheckAnswer(bundle.getString("d"),bundle.getString("question"));
                    break;
                }
            }
        }
        return ketqua;
    }
    public boolean CheckAnswer(String ans, String question)
    {
        if (ans.compareTo(question)==0)
            return true;
        return false;
    }
}
