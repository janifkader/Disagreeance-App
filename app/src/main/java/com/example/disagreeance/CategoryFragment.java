package com.example.disagreeance;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CategoryFragment extends Fragment {
    private static final String CATEGORY = "c";
    private String category;
    Map<String, Object> data;
    TextView questionText;
    TextView AText;
    TextView BText;

    Button regenerate, continueButton;

    String ques;
    String optionA;
    String optionB;

    public CategoryFragment() {
        // Required empty public constructor
    }
    public static CategoryFragment newInstance(String c) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, c);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        regenerate = view.findViewById(R.id.regenerate);
        continueButton = view.findViewById(R.id.continueButton);
        questionText = (TextView)view.findViewById(R.id.question);
        AText = (TextView)view.findViewById(R.id.option1);
        BText = (TextView)view.findViewById(R.id.option2);

        DatabaseReference db = FirebaseDatabase.getInstance(
                "https://disagreeance-c7dfe-default-rtdb.firebaseio.com/").getReference();
        Random rand = new Random();
        Task<DataSnapshot> task = getDataFromFirebase(db.child(category));
        Task<List<Object>> taskComplete = Tasks.whenAllSuccess(task);
        taskComplete.addOnSuccessListener(aVoid -> {
            if (task.getResult() == null) {
                Toast.makeText(getContext(), "There was an error retrieving the data", Toast.LENGTH_SHORT).show();
            }
            data = (Map<String, Object>) (task.getResult().child(Integer.toString(rand.nextInt(3))).getValue());
            ques = data.get("Question").toString();
            optionA = data.get("A").toString();
            optionB = data.get("B").toString();
            questionText.setText(ques);
            String temp = "A: " + optionA;
            AText.setText(temp);
            temp = "B: " + optionB;
            BText.setText(temp);
        });
        regenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.regenerate);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.categoryFragment, CategoryFragment.newInstance(category));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button)view.findViewById(R.id.continueButton);
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.categoryFragment, SidesFragment.newInstance(ques, optionA, optionB));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        view.findViewById(R.id.returnHome).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ImageButton button = (ImageButton)view.findViewById(R.id.returnHome);
                button.setBackgroundColor(0x0404cf);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.categoryFragment, new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
    private Task<DataSnapshot> getDataFromFirebase(DatabaseReference ref) {
        final TaskCompletionSource<DataSnapshot> taskCompletionSource = new TaskCompletionSource<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskCompletionSource.setResult(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                taskCompletionSource.setException(error.toException());
            }
        });
        return taskCompletionSource.getTask();
    }
}