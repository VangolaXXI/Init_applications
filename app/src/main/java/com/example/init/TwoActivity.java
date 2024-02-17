package com.example.init;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TwoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Button loginButton = findViewById(R.id.loginButton1);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик нажатия на кнопку
                Intent intent = new Intent(TwoActivity.this, persanal_card.class);
                startActivity(intent);
            }
        });
    }



}
