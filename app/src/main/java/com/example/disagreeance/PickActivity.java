package com.example.disagreeance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickActivity extends AppCompatActivity {
    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pick);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pickActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.sportsButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.sportsButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pickActivity, CategoryFragment.newInstance("Sports"));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        findViewById(R.id.musicButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.musicButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pickActivity, CategoryFragment.newInstance("Music"));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        findViewById(R.id.moviesButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.moviesButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pickActivity, CategoryFragment.newInstance("Movies"));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        findViewById(R.id.tvButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.tvButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pickActivity, CategoryFragment.newInstance("TV"));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        findViewById(R.id.politicsButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.politicsButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pickActivity, CategoryFragment.newInstance("Politics"));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        findViewById(R.id.backHome).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ImageButton button = (ImageButton)findViewById(R.id.backHome);
                button.setBackgroundColor(0x0404cf);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pickActivity, new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}