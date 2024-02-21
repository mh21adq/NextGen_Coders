package com.example.next;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class ReadingMaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_material);

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("content.pdf")
                .load();
    }
}
