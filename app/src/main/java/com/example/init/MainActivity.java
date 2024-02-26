package com.example.init;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements StartMenu.OnStartMenuButtonClicked, FirstFragment.OnFirstFragmentButtonClicked, Mainmenu.OnSmartQClickListener, SmartQ.OnSmartQButtonClickListener {


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
        mainmenu.setOnSmartQClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.basic_frame, mainmenu, "MainmenuFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSmartQClicked() {
        // Пользователь нажал на кнопку в MainmenuFragment, открываем SmartQFragment
        Mainmenu mainmenu = (Mainmenu) getSupportFragmentManager().findFragmentByTag("MainmenuFragment");
        if (mainmenu != null) {
            SmartQ smartQ = new SmartQ();
            smartQ.setOnSmartQButtonClickListener(this);
            smartQ.setMainmenu(mainmenu); // передаем существующий экземпляр Mainmenu
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.basic_frame, smartQ)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onSmartQButtonClicked(Mainmenu mainmenu) {
        // Пользователь нажал на кнопку в SmartQFragment, возвращаемся к MainmenuFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.basic_frame, mainmenu)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onLeaderboardButtonClick() {
        // Открываем LeaderboardFragment
        Leaderboard leaderboardFragment = new Leaderboard();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.basic_frame, leaderboardFragment)
                .addToBackStack(null)
                .commit();
    }
}
