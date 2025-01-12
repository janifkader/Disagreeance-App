package com.example.disagreeance;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SidesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SidesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String QUESTION = "q";
    private static final String OPTIONA = "A";
    private static final String OPTIONB = "B";

    // TODO: Rename and change types of parameters
    private String question;
    private String optionA;
    private String optionB;

    Button optA;
    Button optB;

    public SidesFragment() {

    }

    public static SidesFragment newInstance(String q, String A, String B) {
        SidesFragment fragment = new SidesFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, q);
        args.putString(OPTIONA, A);
        args.putString(OPTIONB, B);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION);
            optionA = getArguments().getString(OPTIONA);
            optionB = getArguments().getString(OPTIONB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sides, container, false);
        optA = (Button)view.findViewById(R.id.pickA);
        optB = (Button)view.findViewById(R.id.pickB);
        optA.setText(optionA);
        optB.setText(optionB);
        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optA.setBackgroundColor(0xf59f9f);
                Runnable r = new Runnable() {
                    @Override
                    public void run(){
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.sidesFragment, ReadyFragment.newInstance(question, optionA, optionB));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 250);

            }
        });
        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optB.setBackgroundColor(0x4baefa);
                Runnable r = new Runnable() {
                    @Override
                    public void run(){
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.sidesFragment, ReadyFragment.newInstance(question, optionB, optionA));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 250);
            }
        });
        return view;
    }
}