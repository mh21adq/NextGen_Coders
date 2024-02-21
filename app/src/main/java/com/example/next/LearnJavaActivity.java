package com.example.next;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class LearnJavaActivity extends AppCompatActivity {
    private Spinner chaptersSpinner;
    private RecyclerView lessonsRecyclerView;
    private LessonAdapter lessonAdapter;
    private List<Lesson> lessonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_java);

        // Setup toolbar
     /*   Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
*/

        // Initialize spinner and recycler view
        chaptersSpinner = findViewById(R.id.chaptersSpinner);
        lessonsRecyclerView = findViewById(R.id.lessonsRecyclerView);

        setupChaptersSpinner();
        setupLessonsRecyclerView();
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
                // Optional: Handle the case where no chapter selection is made
            }
        });
    }

    private void setupLessonsRecyclerView() {
        lessonsList = new ArrayList<>();
        lessonAdapter = new LessonAdapter(this, lessonsList);
        lessonsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lessonsRecyclerView.setAdapter(lessonAdapter);
    }

    private void updateLessonsForChapter(int chapterIndex) {
        int lessonArrayId = getResources().getIdentifier("chapter_" + (chapterIndex + 1) + "_lessons",
                "array", getPackageName());
        String[] lessonTitles = getResources().getStringArray(lessonArrayId);

        lessonsList.clear();
        for (String title : lessonTitles) {
            lessonsList.add(new Lesson(title, false)); // Assuming none are completed
        }
        lessonAdapter.notifyDataSetChanged(); // Notify the adapter of the data change
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
