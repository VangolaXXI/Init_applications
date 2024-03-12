package com.example.init.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.init.R;


public class Settings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button btBack = view.findViewById(R.id.bt_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });

        return view;
    }

    private void onBackButtonClick() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
