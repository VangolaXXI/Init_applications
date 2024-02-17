package com.example.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



public class persanal_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persanal_card);


    }
    public void onLinearLayoutClick(View view) {
        ImageView iconImageView = findViewById(R.id.icon2);
        iconImageView.setImageResource(R.drawable.smartiq_activi);

        // Код для открытия другой активности
        Intent intent = new Intent(this, SmartIQ.class);
        startActivity(intent);
    }
}