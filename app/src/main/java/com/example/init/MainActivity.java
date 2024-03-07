package com.example.init;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements StartMenu.OnStartMenuButtonClicked, FirstFragment.OnFirstFragmentButtonClicked, Mainmenu.OnSmartQClickListener, SmartIQ.OnSmartQButtonClickListener {
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
            SmartIQ smartQ = new SmartIQ();
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
    @Override
    public void onSettingsButtonClick() {
        // Создаем экземпляр фрагмента Settings
        Settings settingsFragment = new Settings();

        // Создаем транзакцию для замены текущего фрагмента на фрагмент Settings
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Задаем анимацию для входа (отображения) нового фрагмента
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        // Заменяем текущий фрагмент на фрагмент Settings с заданной анимацией
        transaction.replace(R.id.basic_frame, settingsFragment);

        // Добавляем транзакцию в стек возврата
        transaction.addToBackStack(null);

        // Применяем транзакцию
        transaction.commit();
    }

}
