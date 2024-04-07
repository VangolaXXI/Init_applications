package com.example.init.screens;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.init.game.Game;
import com.example.init.R;
import com.example.init.game.playActivity;

public class SmartIQ extends Fragment {
    public interface OnSmartQButtonClickListener {
        void onSmartQButtonClicked(Mainmenu mainmenu);
        void onLeaderboardButtonClick();
        void onSettingsButtonClick();
    }

    private Mainmenu mainmenu;
    private OnSmartQButtonClickListener smartQButtonClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_iq, container, false);

        Button btMain = view.findViewById(R.id.bt_main);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMainButtonClick();
            }
        });

        Button btTab = view.findViewById(R.id.bt_tb);
        btTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabButtonClick();
            }
        });

        Button btSettings = view.findViewById(R.id.bt_st);
        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSettingsButtonClick();
            }
        });

        Button btNew = view.findViewById(R.id.bt_new);
        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewButtonClick();
            }
        });

        return view;
    }

    public void setOnSmartQButtonClickListener(OnSmartQButtonClickListener listener) {
        this.smartQButtonClickListener = listener;
    }

    private void onMainButtonClick() {
        if (smartQButtonClickListener != null && mainmenu != null) {
            smartQButtonClickListener.onSmartQButtonClicked(mainmenu);
        }
    }

    private void onTabButtonClick() {
        if (smartQButtonClickListener != null) {
            smartQButtonClickListener.onLeaderboardButtonClick();
        }
    }

    private void onSettingsButtonClick() {
        if (smartQButtonClickListener != null) {
            smartQButtonClickListener.onSettingsButtonClick();
        }
    }

    private void onNewButtonClick() {
        // Создаем интент для запуска GameActivity
        Intent intent = new Intent(getActivity(), playActivity.class);
        startActivity(intent);
    }

    public void setMainmenu(Mainmenu mainmenu) {
        this.mainmenu = mainmenu;
    }
}
