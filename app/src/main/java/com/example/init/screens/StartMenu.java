package com.example.init.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.init.R;


public class StartMenu extends Fragment {
    private OnStartMenuButtonClicked listener;



    public interface OnStartMenuButtonClicked {
        void onStartMenuButtonClick();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);

        Button startButton = view.findViewById(R.id.loginButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onStartMenuButtonClick();
                }
            }
        });

        return view;
    }

    public void setOnStartMenuButtonClickListener(OnStartMenuButtonClicked listener) {
        this.listener = listener;
    }
}
