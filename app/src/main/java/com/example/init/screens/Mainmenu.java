package com.example.init.screens;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.init.R;

public class Mainmenu extends Fragment {
    public interface OnSmartQClickListener {
        void onSmartQClicked();
    }

    private ImageView imageView1, imageView3, imageView4;
    private Drawable originalIcon1, originalIcon3, originalIcon4;
    private Drawable currentIcon1, currentIcon3, currentIcon4;
    private OnSmartQClickListener smartQClickListener; // Поле для слушателя

    private Main_menu mainMenu = new Main_menu();
    private News news = new News();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainmenu, container, false);

        originalIcon1 = getResources().getDrawable(R.drawable.home_activ);
        originalIcon3 = getResources().getDrawable(R.drawable.news_activ);
        originalIcon4 = getResources().getDrawable(R.drawable.login_activ);
        imageView1 = view.findViewById(R.id.icon1);
        imageView3 = view.findViewById(R.id.icon3);
        imageView4 = view.findViewById(R.id.icon4);

        imageView1.setImageDrawable(originalIcon1);
        currentIcon1 = imageView1.getDrawable();
        currentIcon3 = imageView3.getDrawable();
        currentIcon4 = imageView4.getDrawable();

        setNewFragment(mainMenu);

        // Установка обработчиков событий для кликов по различным элементам в макете
        view.findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(mainMenu);
                imageView1.setImageDrawable(originalIcon1);
                imageView3.setImageDrawable(currentIcon3);
                imageView4.setImageDrawable(currentIcon4);
            }
        });

        view.findViewById(R.id.layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (smartQClickListener != null) {
                    smartQClickListener.onSmartQClicked();
                }
            }
        });

        view.findViewById(R.id.layout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(news);
                imageView1.setImageDrawable(getResources().getDrawable(R.drawable.home));
                imageView3.setImageDrawable(originalIcon3);
                imageView4.setImageDrawable(currentIcon4);
            }
        });

        view.findViewById(R.id.layout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(mainMenu);
                imageView1.setImageDrawable(getResources().getDrawable(R.drawable.home));
                imageView3.setImageDrawable(currentIcon3);
                imageView4.setImageDrawable(originalIcon4);
            }
        });

        return view;
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.mainmenu_frame, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    // Метод для установки слушателя
    public void setOnSmartQClickListener(OnSmartQClickListener listener) {
        this.smartQClickListener = listener;
    }

    // Метод для вызова события извне
    public void performSmartQClick() {
        if (smartQClickListener != null) {
            smartQClickListener.onSmartQClicked();
        }
    }
}
