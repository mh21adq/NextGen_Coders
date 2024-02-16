package com.example.next.ui.ai;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.next.R;

public class ChatGPTActivity extends AppCompatActivity {

    private LinearLayout chatContainer;
    private EditText userInputEditText;
    private ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgpt);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        chatContainer = findViewById(R.id.chatContainer);
        userInputEditText = findViewById(R.id.userInputEditText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(view -> {
            String userInput = userInputEditText.getText().toString().trim();
            if (!userInput.isEmpty()) {
                addMessageToChat(userInput, true); // Add user message to chat
                userInputEditText.setText(""); // Clear input field

                // Simulate sending message to API and receiving response
                String apiResponse = "This is a simulated response to: " + userInput;
                addMessageToChat(apiResponse, false); // Add API response to chat
            }
        });
    }

    private void addMessageToChat(String message, boolean isUserMessage) {
        TextView messageView = new TextView(this);
        messageView.setText(message);
        messageView.setPadding(16, 16, 16, 16);
        if (isUserMessage) {
            // Style for user message
            messageView.setBackground(getResources().getDrawable(R.drawable.user_message_background));
            messageView.setTextColor(getResources().getColor(android.R.color.black));
        } else {
            // Style for API response
            messageView.setBackground(getResources().getDrawable(R.drawable.api_response_background));
            messageView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        chatContainer.addView(messageView);

        // Scroll to the bottom to show the latest message
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
