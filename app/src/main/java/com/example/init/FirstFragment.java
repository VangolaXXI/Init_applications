package com.example.init;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstFragment extends Fragment {

    private OnFirstFragmentButtonClicked listener;

    public interface OnFirstFragmentButtonClicked {
        void onFirstFragmentButtonClick();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        Button nextButton = view.findViewById(R.id.loginButton1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onFirstFragmentButtonClick();
                }
            }
        });

        return view;
    }

    public void setOnFirstFragmentButtonClickListener(OnFirstFragmentButtonClicked listener) {
        this.listener = listener;
    }

}