package com.cuong.project_q;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class ResultActivity extends DialogFragment {

    Button finishButton;
    TextView txtScore;
    private int score;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_result,null);
        builder.setView(view);
        txtScore = (TextView)view.findViewById(R.id.TxtResult);
        finishButton = (Button)view.findViewById(R.id.BtFinish);
        Bundle bundle =getArguments();
        txtScore.setText("Score: "+bundle.getInt("result"));
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.create();

    }
    public static ResultActivity newInstance(int Score){
        ResultActivity dialog = new ResultActivity();
        Bundle args = new Bundle();
        args.putInt("result",Score);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        {

            score = getArguments().getInt("Score",0);
        }
    }

}
