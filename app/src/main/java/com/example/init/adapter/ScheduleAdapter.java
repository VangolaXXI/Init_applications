package com.example.init.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.init.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleLesson> scheduleList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String day);
    }

    public ScheduleAdapter(List<ScheduleLesson> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleLesson scheduleLesson = scheduleList.get(position);
        holder.bind(scheduleLesson);

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                int dayOfWeek = position; // Получаем номер дня недели в зависимости от позиции
                String day = getDayOfWeek(dayOfWeek); // Получаем название дня
                listener.onItemClick(day);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lessonNameTextView;
        private TextView timeTextView;
        private TextView cabinetTextView;
        private TextView groupTextView;
        private TextView teacherTextView;
        private TextView dayTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonNameTextView = itemView.findViewById(R.id.lessonNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            cabinetTextView = itemView.findViewById(R.id.cabinetTextView);
            groupTextView = itemView.findViewById(R.id.groupTextView);
            teacherTextView = itemView.findViewById(R.id.teacherTextView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
        }

        public void bind(ScheduleLesson scheduleLesson) {

            String groups = "";
            if (scheduleLesson.getGroup() != null) {
                groups = TextUtils.join(", ", scheduleLesson.getGroup());
            }
            lessonNameTextView.setText(scheduleLesson.getLessonName());
            timeTextView.setText(scheduleLesson.getTime());
            cabinetTextView.setText(scheduleLesson.getCabinet());
            groupTextView.setText(groups);
            teacherTextView.setText(scheduleLesson.getTeacher());
            dayTextView.setText(scheduleLesson.getDay());
        }
    }

    // Метод для преобразования номера дня недели в его название
    private String getDayOfWeek(int dayOfWeek) {
        String[] days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
        if (dayOfWeek >= 0 && dayOfWeek < days.length) {
            return days[dayOfWeek];
        }
        return "";
    }
}

