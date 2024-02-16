package com.example.next;

public class Lesson {
    private String title;
    private boolean isCompleted; // To track if the lesson is completed

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

    // Add setters if needed
}
