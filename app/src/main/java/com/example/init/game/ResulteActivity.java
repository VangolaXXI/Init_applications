package com.example.init.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.init.R;

public class ResulteActivity extends AppCompatActivity {

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulte);
        textView = findViewById(R.id.textView);
        int score = getIntent().getIntExtra("Результат",0);
        textView.setText("Счет : " + score);

        findViewById(R.id.btn_restart).setOnClickListener(
                restart->{
                    finish();
                }
        );
    }
}