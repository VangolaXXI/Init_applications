package com.example.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SmartIQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_iq);

        Button buttonMain = findViewById(R.id.bt_main);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создать Intent для открытия другой активити
                Intent intent = new Intent(SmartIQ.this, persanal_card.class);
                // Запустить активити
                startActivity(intent);
                finish();
            }
        });
    }
}