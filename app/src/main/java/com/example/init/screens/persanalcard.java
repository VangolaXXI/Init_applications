package com.example.init.screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.init.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class persanalcard extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persanal_card, container, false);
        ImageView avatar1 = view.findViewById(R.id.circleImageView);
        TextView fullnameTextView = view.findViewById(R.id.fullnameTextView);
        TextView groupTextView = view.findViewById(R.id.groupTextView);
        TextView birthdayTextView = view.findViewById(R.id.birthdayTextView);
        TextView emailTextView = view.findViewById(R.id.emailTextView);
        TextView innTextView = view.findViewById(R.id.innTextView);
//          Загрузка фото через realtime database+picasso
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference Image = database.getReference("picasso");
//        Image.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value =snapshot.getValue(String.class);
//                Picasso.get().load(value).into(avatar1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        //        String url = "gs://init-bc22d.appspot.com/ilim.jpeg";
//        Glide.with(requireActivity())
//                .load(url)
//                .error(R.drawable.circle)
//                .into(avatar1);






        // Получаем объект SharedPreferences
        SharedPreferences sp = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Извлекаем данные из SharedPreferences
        String fullname = sp.getString("Fullname", "не найдено");
        String group = sp.getString("group", "не найдено");
        String birthday = sp.getString("birthday", "не найдено");
        String email = sp.getString("email", "не найдено");
        String inn = sp.getString("INN", "000000000000");
        String photoUrl = sp.getString("photoUrl", "none image");


        // Устанавливаем данные в соответствующие TextView
        fullnameTextView.setText(fullname);
        groupTextView.setText("Гр." + group);
        birthdayTextView.setText(birthday);
        emailTextView.setText(email);
        innTextView.setText(inn);

        // Загрузка фотографии пользователя с помощью Picasso
        Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.circle) // Заглушка, если фотография не загружена
                .error(R.drawable.logo_main) // Заглушка для случая ошибки загрузки
                .into(avatar1);

        return view;
    }
}
