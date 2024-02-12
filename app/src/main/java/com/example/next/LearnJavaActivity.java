package com.example.next;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.next.databinding.ActivityLearnJavaBinding;

public class LearnJavaActivity extends AppCompatActivity {

    private ActivityLearnJavaBinding binding;
    private LinearLayout buttonsContainerIntro;
    private LinearLayout buttonsContainerMethods;
    private LinearLayout buttonsContainerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this activity
        binding = ActivityLearnJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the Toolbar as the ActionBar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        // Enable the Up button in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Initialize the buttons containers from the layout
        buttonsContainerIntro = findViewById(R.id.buttonsContainer);
        buttonsContainerMethods = findViewById(R.id.buttonsContainer_methods);
        buttonsContainerClass = findViewById(R.id.buttonsContainer_class);

        // Set up the OnClickListener for the Intro section
        LinearLayout introSection = findViewById(R.id.introSection);
        introSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtonVisibility(buttonsContainerIntro); // Pass the relevant container
            }
        });

        // Set up the OnClickListener for the Methods section
        LinearLayout methodsSection = findViewById(R.id.methods_section);
        methodsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtonVisibility(buttonsContainerMethods); // Pass the relevant container
            }
        });

        // Set up the OnClickListener for the Class section
        LinearLayout classSection = findViewById(R.id.class_section);
        classSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtonVisibility(buttonsContainerClass); // Pass the relevant container
            }
        });
        Button syntax=findViewById(R.id.syntax);
        syntax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ContentActivity.class));
            }
        });

    }

    // Method to toggle the visibility of the specified buttons container
    private void toggleButtonVisibility(LinearLayout buttonsContainer) {
        // This will toggle the visibility of the passed container
        if (buttonsContainer.getVisibility() == View.GONE) {
            buttonsContainer.setVisibility(View.VISIBLE);
        } else {
            buttonsContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle the back action
        return true;
    }
}
