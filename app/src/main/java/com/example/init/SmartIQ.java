package com.example.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SmartIQ extends AppCompatActivity {
    private Button buttonMain;
    private Button buttonNewgame;
    private Button buttonTopweekend;
    private Button buttonsettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_iq);

        buttonMain = findViewById(R.id.bt_main);
        buttonNewgame = findViewById(R.id.bt_new);
        buttonTopweekend = findViewById(R.id.bt_tb);
        buttonsettings = findViewById(R.id.bt_st);
        setButtons();
    }
    private void setButtons() {

        View.OnClickListener anotherButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if (viewId == R.id.bt_new) {
//                    // Создание Intent для открытия другой активити
//                    Intent intent = new Intent(SmartIQ.this, persanal_card.class);
//                    // Запуск активити
//                    startActivity(intent);
//                    finish();
                } else if (viewId == R.id.bt_tb) {
                    Intent intent = new Intent(SmartIQ.this, Table_top.class);
                    startActivity(intent);
                    finish();
                } else if (viewId == R.id.bt_st) {
//                    Intent intent = new Intent(SmartIQ.this, persanal_card.class);
//                    startActivity(intent);
//                    finish();
                } else if (viewId == R.id.bt_main) {
                    Intent intent = new Intent(SmartIQ.this, persanal_card.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        buttonMain.setOnClickListener(anotherButtonClickListener);
        buttonNewgame.setOnClickListener(anotherButtonClickListener);
        buttonTopweekend.setOnClickListener(anotherButtonClickListener);
        buttonsettings.setOnClickListener(anotherButtonClickListener);
    }

}