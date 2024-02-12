package com.example.next;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ContentActivity extends AppCompatActivity {

    private LinearLayout contentLayout;
    private Button backButton, nextButton;
    private JSONArray topics;
    private int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        contentLayout = findViewById(R.id.content_layout);
        backButton = findViewById(R.id.back_button);
        nextButton = findViewById(R.id.next_button);

        String jsonContent = loadJSONFromAsset("learning_material.json");
        if (jsonContent != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonContent);
                topics = jsonObj.getJSONArray("topics");
                // Get the topic ID from the intent
                String topicId = getIntent().getStringExtra("TOPIC_ID");
                for (int i = 0; i < topics.length(); i++) {
                    if (topics.getJSONObject(i).getString("id").equals(topicId)) {
                        currentIndex = i;
                        break;
                    }
                }
                updateContent();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        backButton.setOnClickListener(view -> navigateContent(false));
        nextButton.setOnClickListener(view -> navigateContent(true));
    }

    private void navigateContent(boolean isNext) {
        if (isNext && currentIndex < topics.length() - 1) {
            currentIndex++;
        } else if (!isNext && currentIndex > 0) {
            currentIndex--;
        }
        updateContent();
    }

    private void updateContent() {
        contentLayout.removeAllViews();
        if (currentIndex >= 0 && currentIndex < topics.length()) {
            try {
                JSONObject currentTopic = topics.getJSONObject(currentIndex);
                JSONArray contentArray = currentTopic.getJSONArray("content");
                for (int i = 0; i < contentArray.length(); i++) {
                    JSONObject section = contentArray.getJSONObject(i);
                    String type = section.getString("type");
                    String text = section.getString("text");

                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setText(text);
                    textView.setPadding(16, 16, 16, 16);

                    if ("code".equals(type)) {
                        textView.setTypeface(android.graphics.Typeface.MONOSPACE);
                    }

                    contentLayout.addView(textView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            AssetManager manager = getAssets();
            InputStream is = manager.open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
