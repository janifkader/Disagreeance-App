package com.example.disagreeance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DebateFragment extends Fragment {

    private static final String QUESTION = "q";
    private static final String PLAYER1 = "p1";
    private static final String PLAYER2 = "p2";
    private String question;
    private String player1;
    private String player2;
    int roundNumber = 1;
    int time = 60000;

    public DebateFragment() {

    }

    public static DebateFragment newInstance(String q, String p1, String p2) {
        DebateFragment fragment = new DebateFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, q);
        args.putString(PLAYER1, p1);
        args.putString(PLAYER2, p2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION);
            player1 = getArguments().getString(PLAYER1);
            player2 = getArguments().getString(PLAYER2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_debate, container, false);
        TextView query = (TextView)view.findViewById(R.id.questionText);
        TextView player = (TextView)view.findViewById(R.id.playerText);
        TextView round = (TextView)view.findViewById(R.id.roundText);
        TextView countdown = (TextView)view.findViewById(R.id.timer);
        Button button = (Button)view.findViewById(R.id.nextRound);
        query.setText(question);
        player.setText("Player 1: " + player1);
        countDown(countdown, button, time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundNumber++;
                if (roundNumber >= 3 && roundNumber <= 4){
                    round.setText("Counterarguement");
                    time = 30000;
                }
                else if (roundNumber > 6){
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.debateFragment, EndFragment.newInstance(question, player1, player2));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if (roundNumber > 4){
                    round.setText("Rebuttal");
                }
                if (roundNumber % 2 == 0){
                    player.setText("Player 2: " + player2);
                }
                else{
                    player.setText("Player 1: " + player1);
                }
                countdown.setText("1:00");
                countDown(countdown, button, time);
                button.setVisibility(View.GONE);
            }
        });
        return view;
    }
    private void countDown(TextView countdown, Button nextRound, int time){
        new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 1000 >= 10) {
                    countdown.setText("0:" + millisUntilFinished / 1000);
                }
                else{
                    countdown.setText("0:0" + millisUntilFinished / 1000);
                }
            }
            public void onFinish() {
                countdown.setText("STOP!");
                nextRound.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}