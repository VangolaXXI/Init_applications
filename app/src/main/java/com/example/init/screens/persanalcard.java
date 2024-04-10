package com.example.init.screens;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import com.example.init.R;
import com.example.init.adapter.ScheduleAdapter;
import com.example.init.adapter.ScheduleLesson;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class persanalcard extends Fragment {
    private static final String TAG = "PersonalCardFragment"; // Тег для логов

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<ScheduleLesson> scheduleList;
    private TextView textMon, textTue, textWed, textThu, textFri, textSat,emptyMessageTextView;

    private FirebaseFirestore db;
    private String Usergroup;
    private String selectedDay; // По умолчанию выбран понедельник

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persanal_card, container, false);

        recyclerView = view.findViewById(R.id.scheduleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        db = FirebaseFirestore.getInstance();
        scheduleList = new ArrayList<>();

        if (scheduleList != null) {
            adapter = new ScheduleAdapter(scheduleList);
            recyclerView.setAdapter(adapter);
        } else {
            Log.e(TAG, "Ошибка: scheduleList равен null");
        }




        SharedPreferences sp = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String fullname = sp.getString("Fullname", "не найдено");
        Usergroup = sp.getString("group", "не найдено");
        String birthday = sp.getString("birthday", "не найдено");
        String email = sp.getString("email", "не найдено");
        String inn = sp.getString("INN", "000000000000");
        String photoUrl = sp.getString("photoUrl", "none image");
        String username = sp.getString("username", "none username");

        loadScheduleData(username, selectedDay);

        ImageView avatar1 = view.findViewById(R.id.circleImageView);
        TextView fullnameTextView = view.findViewById(R.id.fullnameTextView);
        TextView groupTextView = view.findViewById(R.id.groupTextView);
        TextView birthdayTextView = view.findViewById(R.id.birthdayTextView);
        TextView emailTextView = view.findViewById(R.id.emailTextView);
        TextView innTextView = view.findViewById(R.id.innTextView);

        fullnameTextView.setText(fullname);
        groupTextView.setText("Гр." + Usergroup);
        birthdayTextView.setText(birthday);
        emailTextView.setText(email);
        innTextView.setText(inn);

        Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.circle)
                .error(R.drawable.logo_main)
                .into(avatar1);

        textMon = view.findViewById(R.id.textMon);
        textTue = view.findViewById(R.id.textTue);
        textWed = view.findViewById(R.id.textWed);
        textThu = view.findViewById(R.id.textThu);
        textFri = view.findViewById(R.id.textFri);
        textSat = view.findViewById(R.id.textSat);
        emptyMessageTextView = view.findViewById(R.id.emptyMessageTextView); // Инициализируем emptyMessageTextView


        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Получаем текущий день недели
        selectedDay = getDayOfWeek(dayOfWeek); // Получаем строковое представление текущего дня недели
        loadScheduleData(username, selectedDay); // Загружаем расписание для выбранного дня по умолчанию

        setDaySelectedStyle(getTextViewByDay(selectedDay));
        adapter.setOnItemClickListener(day -> {
            selectedDay = day;
            loadScheduleData(username, selectedDay);
        });



        textMon.setOnClickListener(v -> {
            selectedDay = "Понедельник";
            loadScheduleData(username, selectedDay);
            setDaySelectedStyle(textMon);
            resetDayStyleExcept(textMon);
        });

        textTue.setOnClickListener(v -> {
            selectedDay = "Вторник";
            loadScheduleData(username, selectedDay);
            setDaySelectedStyle(textTue);
            resetDayStyleExcept(textTue);
        });

        textWed.setOnClickListener(v -> {
            selectedDay = "Среда";
            loadScheduleData(username, selectedDay);
            setDaySelectedStyle(textWed);
            resetDayStyleExcept(textWed);
        });

        textThu.setOnClickListener(v -> {
            selectedDay = "Четверг";
            loadScheduleData(username, selectedDay);
            setDaySelectedStyle(textThu);
            resetDayStyleExcept(textThu);
        });

        textFri.setOnClickListener(v -> {
            selectedDay = "Пятница";
            loadScheduleData(username, selectedDay);
            setDaySelectedStyle(textFri);
            resetDayStyleExcept(textFri);
        });

        textSat.setOnClickListener(v -> {
            selectedDay = "Суббота";
            loadScheduleData(username, selectedDay);
            setDaySelectedStyle(textSat);
            resetDayStyleExcept(textSat);
        });

        return view;
    }

    private void loadScheduleData(String username, String selectedDay) {
        Log.d(TAG, "Loading schedule data for group: " + Usergroup + ", day: " + selectedDay);
        db.collection("subject")
                .whereArrayContains("group", Usergroup) // Фильтр по группе пользователя
                .whereEqualTo("day", selectedDay)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    scheduleList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String lessonName = documentSnapshot.getString("lesson_name");
                        String time = documentSnapshot.getString("time");
                        String cabinet = documentSnapshot.getString("cabinet");
                        List<String> groupFromDB = (List<String>) documentSnapshot.get("group");
                        String teacher = documentSnapshot.getString("teacher");
                        String day = documentSnapshot.getString("day");
                        Log.d(TAG, "Group from DB: " + groupFromDB);
                        Log.d(TAG, "Username: " + username);
                        ScheduleLesson scheduleLesson = new ScheduleLesson(lessonName, time, cabinet, groupFromDB, teacher, day);
                        scheduleList.add(scheduleLesson);
                    }

                    if (scheduleList != null && !scheduleList.isEmpty()) {
                        emptyMessageTextView.setVisibility(View.INVISIBLE);
                        adapter = new ScheduleAdapter(scheduleList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(day -> {
                            String selectedDayTemp = day; // Создаем временную переменную
                            loadScheduleData(username, selectedDayTemp);
                        });
                    } else {
                        emptyMessageTextView.setVisibility(View.VISIBLE);
                    }

                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "Schedule data loaded successfully!");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(requireContext(), "Ошибка загрузки данных расписания!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error loading schedule data: " + e.getMessage());
                    emptyMessageTextView.setText("Ошибка загрузки данных расписания!");
                    emptyMessageTextView.setVisibility(View.VISIBLE);
                });
    }

    // Метод для преобразования числового значения дня недели в строку
    private String getDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "Понедельник";
            case Calendar.TUESDAY:
                return "Вторник";
            case Calendar.WEDNESDAY:
                return "Среда";
            case Calendar.THURSDAY:
                return "Четверг";
            case Calendar.FRIDAY:
                return "Пятница";
            case Calendar.SATURDAY:
                return "Суббота";
            default:
                return "Понедельник"; // По умолчанию, если не удалось определить день недели
        }
    }

    private TextView getTextViewByDay(String day) {
        switch (day) {
            case "Понедельник":
                return textMon;
            case "Вторник":
                return textTue;
            case "Среда":
                return textWed;
            case "Четверг":
                return textThu;
            case "Пятница":
                return textFri;
            case "Суббота":
                return textSat;
            default:
                return textMon; // По умолчанию, если не удалось определить день недели
        }
    }


    private void setDaySelectedStyle(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_day_background);
    }

    private void resetDayStyleExcept(TextView selectedTextView) {
        TextView[] allTextViews = {textMon, textTue, textWed, textThu, textFri, textSat};
        for (TextView textView : allTextViews) {
            if (textView != selectedTextView) {
                textView.setBackgroundResource(android.R.color.transparent);
            }
        }
    }
}
