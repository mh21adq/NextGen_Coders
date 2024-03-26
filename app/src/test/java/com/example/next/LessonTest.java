package com.example.next;
import com.example.next.*;
import com.example.next.lessons.Lesson;

import org.junit.Test;
import static org.junit.Assert.*;

public class LessonTest {

    @Test
    public void testLessonInitialization() {
        String expectedTitle = "Introduction to Java";
        boolean expectedCompletionStatus = false;

        Lesson lesson = new Lesson(expectedTitle, expectedCompletionStatus);

        assertEquals("Title should match", expectedTitle, lesson.getTitle());
        assertEquals("Completion status should match", expectedCompletionStatus, lesson.isCompleted());
    }

    @Test
    public void testLessonCompletion() {
        Lesson lesson = new Lesson("Variables and Data Types", false);

        // Simulate marking the lesson as completed
        lesson = new Lesson(lesson.getTitle(), true);

        assertTrue("Lesson should be marked as completed", lesson.isCompleted());
    }
}
