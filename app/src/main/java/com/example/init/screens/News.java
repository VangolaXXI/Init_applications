package com.example.init.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.init.R;


public class News extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public News() {
        // Required empty public constructor
    }

    public static News newInstance(String param1, String param2) {
        News fragment = new News();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        FrameLayout FrameLayout1 = view.findViewById(R.id.news_button_1);
        FrameLayout FrameLayout2 = view.findViewById(R.id.news_button_2);
        FrameLayout frameLayout3 = view.findViewById(R.id.news_button_3);
        FrameLayout FrameLayout4 = view.findViewById(R.id.news_button_4);

        FrameLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://www.youtube.com/watch?si=JXpDwew85UnvWjhs&v=1OzGrXpuw54&feature=youtu.be");
            }
        });

        FrameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://init.arabaevksu.edu.kg/novosti/zhmti-zhalpy-professorduk-okutuuchular-zhamaaty-kyz-zhigit-sarmerden-oyunu-uyushturuldu/");
            }
        });

        frameLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://init.arabaevksu.edu.kg/novosti/27-11-23g-uchastvovali-v-gostevoj-lekczii-na-t/");
            }
        });

        FrameLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://init.arabaevksu.edu.kg/novosti/kmuda-yunyj-programmist-attuu-konkurs-өtүүdө/");
            }
        });

        return view;
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}