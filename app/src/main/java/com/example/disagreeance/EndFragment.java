package com.example.disagreeance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndFragment extends Fragment {

    private static final String QUESTION = "q";
    private static final String PLAYER1 = "p1";
    private static final String PLAYER2 = "p2";
    private String question;
    private String player1;
    private String player2;

    public EndFragment() {

    }
    public static EndFragment newInstance(String q, String p1, String p2) {
        EndFragment fragment = new EndFragment();
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

        View view = inflater.inflate(R.layout.fragment_end, container, false);
        TextView genQuest = (TextView)view.findViewById(R.id.finalQuestionText);
        TextView winner = (TextView)view.findViewById(R.id.whoWon);
        TextView genSide1 = (TextView)view.findViewById(R.id.player1Text);
        TextView genSide2 = (TextView)view.findViewById(R.id.player2Text);
        Button oneWin = (Button)view.findViewById(R.id.player1Win);
        Button twoWin = (Button)view.findViewById(R.id.player2Win);
        Button home = (Button)view.findViewById(R.id.homeButton);
        genQuest.setText(question);
        genSide1.setText("Player 1: " + player1);
        genSide2.setText("Player 2: " + player2);
        oneWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genQuest.setVisibility(View.INVISIBLE);
                genSide1.setVisibility(View.INVISIBLE);
                genSide2.setVisibility(View.INVISIBLE);
                oneWin.setVisibility(View.GONE);
                twoWin.setVisibility(View.GONE);
                home.setVisibility(View.VISIBLE);
                winner.setText("Player 1 Wins!");
            }
        });
        twoWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genQuest.setVisibility(View.INVISIBLE);
                genSide1.setVisibility(View.INVISIBLE);
                genSide2.setVisibility(View.INVISIBLE);
                oneWin.setVisibility(View.GONE);
                twoWin.setVisibility(View.GONE);
                home.setVisibility(View.VISIBLE);
                winner.setText("Player 2 Wins!");
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.endFragment, new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}