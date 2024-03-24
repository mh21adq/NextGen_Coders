package com.example.next.lessons;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.next.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ReadingMaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_material);

        PDFView pdfView = findViewById(R.id.pdfView);

        // Retrieve the PDF file name from the intent extras
        String pdfFileName = getIntent().getStringExtra("PDF_FILE_NAME");

        // Make sure you have a condition to check if pdfFileName is not null
        if (pdfFileName != null && !pdfFileName.isEmpty()) {
            pdfView.fromAsset(pdfFileName)
                    .load();
        } else {
            // Handle the case where pdfFileName is null or empty
            Log.e("PDFView", "No PDF file specified.");
        }
    }
}
