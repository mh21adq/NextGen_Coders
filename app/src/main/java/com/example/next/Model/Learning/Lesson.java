package com.example.next.Model.Learning;

public class Lesson {
    private String title;
    private boolean isCompleted;

    public Lesson(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
