package com.example.init;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements StartMenu.OnStartMenuButtonClicked, FirstFragment.OnFirstFragmentButtonClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Отображаем стартовый фрагмент
        StartMenu startMenu = new StartMenu();
        startMenu.setOnStartMenuButtonClickListener(this); // Устанавливаем обработчик нажатия на кнопку
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.basic_frame, startMenu)
                .commit();
    }

    @Override
    public void onStartMenuButtonClick() {
        // Пользователь нажал на кнопку в стартовом меню, открываем FirstFragment
        FirstFragment firstFragment = new FirstFragment();
        firstFragment.setOnFirstFragmentButtonClickListener(this); // Устанавливаем обработчик нажатия на кнопку
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.basic_frame, firstFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFirstFragmentButtonClick() {
        // Пользователь нажал на кнопку в FirstFragment, открываем MainmenuFragment
        Mainmenu mainmenu = new Mainmenu();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.basic_frame, mainmenu)
                .addToBackStack(null)
                .commit();
    }

}