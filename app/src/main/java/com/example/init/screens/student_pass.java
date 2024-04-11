package com.example.init.screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.init.R;
import com.squareup.picasso.Picasso;

public class student_pass extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_pass, container, false);

        SharedPreferences sp = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        String fullname = sp.getString("Fullname", "не найдено");
        String data_start = sp.getString("data_start", "не найдено");
        String data_end = sp.getString("data_end", "не найдено");
        String direction = sp.getString("direction", "не найдено");
        String photoUrl = sp.getString("photoUrl", "none image");
        String inn = sp.getString("INN", "000000000000");


        ImageView studavatar = view.findViewById(R.id.studavatar);
        TextView studfullname = view.findViewById(R.id.studfullname);
        TextView studid = view.findViewById(R.id.studid);
        TextView directiontextview = view.findViewById(R.id.direction);
        TextView studdatastart = view.findViewById(R.id.studdatastart);
        TextView studdataendtext = view.findViewById(R.id.studdataend);


        studfullname.setText(fullname);
        directiontextview.setText(direction);
        studdatastart.setText(data_start);
        studdataendtext.setText(data_end);
        studid.setText(inn);

        Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.circle)
                .error(R.drawable.logo_main)
                .into(studavatar);


        return view;
    }
}
