package com.example.next.lessons.learnJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.next.R;
import com.example.next.lessons.LearningInterface;
import com.example.next.lessons.Lesson;
import com.example.next.lessons.LessonAdapter;
import com.example.next.lessons.ReadingMaterialActivity;

import java.util.ArrayList;
import java.util.List;

public class LearnJavaActivity extends AppCompatActivity implements LearningInterface {
    private Spinner chaptersSpinner;
    private RecyclerView lessonsRecyclerView;
    private LessonAdapter lessonAdapter;
    private List<Lesson> lessonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_java);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        chaptersSpinner = findViewById(R.id.chaptersSpinner);
        lessonsRecyclerView = findViewById(R.id.lessonsRecyclerView);

        setupChaptersSpinner();
        setupLessonsRecyclerView();
    }

    private void setupLessonsRecyclerView() {
        lessonsList = new ArrayList<>();
        lessonAdapter = new LessonAdapter(this, lessonsList, this);
        lessonsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lessonsRecyclerView.setAdapter(lessonAdapter);
    }

    private void setupChaptersSpinner() {
        ArrayAdapter<CharSequence> chapterAdapter = ArrayAdapter.createFromResource(this,
                R.array.chapters_array, android.R.layout.simple_spinner_item);
        chapterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chaptersSpinner.setAdapter(chapterAdapter);
        chaptersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateLessonsForChapter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void updateLessonsForChapter(int chapterIndex) {
        int lessonArrayId = getResources().getIdentifier("chapter_" + (chapterIndex + 1) + "_lessons",
                "array", getPackageName());
        String[] lessonTitles = getResources().getStringArray(lessonArrayId);

        lessonsList.clear();
        for (String title : lessonTitles) {
            lessonsList.add(new Lesson(title, false));
        }
        lessonAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLessonButtonClicked(Lesson lesson) {
        Intent intent = new Intent(LearnJavaActivity.this, ReadingMaterialActivity.class);
        // Assuming you have a method that correctly maps the lesson title to the PDF filename
        String pdfFileName = getPDFFileName(lesson.getTitle());
        intent.putExtra("PDF_FILE_NAME", pdfFileName);
        startActivity(intent);
    }

    private String getPDFFileName(String lessonTitle) {
        // Replace spaces with underscores and convert to lower case to match the PDF filenames in the assets folder
        return lessonTitle.toLowerCase().replaceAll("\\s+", "_") + ".pdf";
    }

}
