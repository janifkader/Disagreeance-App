package com.example.disagreeance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInAnonymously()
                .addOnCompleteListener(task -> {
                });
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.ruleButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.ruleButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.homeFragment, new RulesFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        view.findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.startButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                Intent myIntent = new Intent(getContext(), PickActivity.class);
                getContext().startActivity(myIntent);
            }
        });
        return view;
    }
}