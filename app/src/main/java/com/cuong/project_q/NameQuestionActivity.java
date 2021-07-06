package com.cuong.project_q;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NameQuestionActivity extends Fragment {


    ImageButton ans1, ans2, ans3, ans4;
    TextView question;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {

        View view = inflater.inflate(R.layout.activity_name_question, container, false);
        ans1 = (ImageButton) view.findViewById(R.id.ImaBt1);
        ans2 = (ImageButton) view.findViewById(R.id.ImaBt2);
        ans3 = (ImageButton) view.findViewById(R.id.ImaBt3);
        ans4 = (ImageButton) view.findViewById(R.id.ImaBt4);
        question = (TextView) view.findViewById(R.id.Txt_Question_flag_name);
        inputData();
        return view;
    }
    public void inputData() {
        Bundle bundle =getArguments();
        if(bundle!=null) {
            String h=bundle.getString("question")+"'s flag is?";
            question.setText(h);
            ans1.setImageResource(getActivity().getResources().getIdentifier(bundle.getString("a"),"drawable","com.cuong.project_q"));
            ans2.setImageResource(getActivity().getResources().getIdentifier(bundle.getString("b"),"drawable","com.cuong.project_q"));
            ans3.setImageResource(getActivity().getResources().getIdentifier(bundle.getString("c"),"drawable","com.cuong.project_q"));
            ans4.setImageResource(getActivity().getResources().getIdentifier(bundle.getString("d"),"drawable","com.cuong.project_q"));
        }
    }
    public boolean checkNameQuestion(View view)
    {
        Bundle bundle =getArguments();
        boolean result=false;
        if(bundle!=null) {
            switch (view.getId()) {
                case R.id.ImaBt1: {
                  result= checkAnswer(bundle.getString("a"),bundle.getString("question"));
                    break;
                }
                case R.id.ImaBt2:
                {
                    result= checkAnswer(bundle.getString("b"),bundle.getString("question"));
                    break;
                }
                case R.id.ImaBt3:
                {
                   result= checkAnswer(bundle.getString("c"),bundle.getString("question"));
                    break;
                }
                case R.id.ImaBt4:
                {
                   result= checkAnswer(bundle.getString("d"),bundle.getString("question"));
                    break;
                }
            }
        }
        return result;
    }
    public boolean checkAnswer(String ans, String question)
    {
        if (ans.compareTo(question)==0)
            return true;
        return false;
    }

}





