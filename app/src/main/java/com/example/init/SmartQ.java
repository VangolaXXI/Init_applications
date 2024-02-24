package com.example.init;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SmartQ extends Fragment {
    public interface OnSmartQButtonClickListener {
        void onSmartQButtonClicked(Mainmenu mainmenu);
    }

    private Mainmenu mainmenu;
    private OnSmartQButtonClickListener smartQButtonClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_q, container, false);

        Button btMain = view.findViewById(R.id.bt_main);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });

        return view;
    }

    public void setOnSmartQButtonClickListener(OnSmartQButtonClickListener listener) {
        this.smartQButtonClickListener = listener;
    }

    private void onButtonClick() {
        if (smartQButtonClickListener != null && mainmenu != null) {
            smartQButtonClickListener.onSmartQButtonClicked(mainmenu);
        }
    }

    public void setMainmenu(Mainmenu mainmenu) {
        this.mainmenu = mainmenu;
    }
}
