package com.example.disagreeance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadyFragment extends Fragment {

    private static final String QUESTION = "q";
    private static final String PLAYER1 = "p1";
    private static final String PLAYER2 = "p2";
    private String question;
    private String player1;
    private String player2;
    TextView countdown;

    public ReadyFragment() {

    }

    public static ReadyFragment newInstance(String q, String p1, String p2) {
        ReadyFragment fragment = new ReadyFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ready, container, false);
        countdown = (TextView)view.findViewById(R.id.count);
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdown.setText(Long.toString(millisUntilFinished / 1000));
            }
            public void onFinish() {
                countdown.setText("GO!");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.readyFragment, DebateFragment.newInstance(question, player1, player2));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                };
                Handler h = new Handler();
                h.postDelayed(r, 500);

            }
        }.start();
        return view;
    }
}