package com.example.init.adapter;

import java.util.List;

public class ScheduleLesson {
    private String lessonName;
    private String time;
    private String cabinet;
    private List<String> group;
    private String teacher;
    private String day;

    public ScheduleLesson(String lessonName, String time, String cabinet, List<String> group, String teacher, String day) {
        this.lessonName = lessonName;
        this.time = time;
        this.cabinet = cabinet;
        this.group = group;
        this.teacher = teacher;
        this.day = day;
    }

    // Getters for each field

    public String getLessonName() {
        return lessonName;
    }

    public String getTime() {
        return time;
    }

    public String getCabinet() {
        return cabinet;
    }

    public List<String>getGroup() {
        return group;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDay() {
        return day;
    }
}