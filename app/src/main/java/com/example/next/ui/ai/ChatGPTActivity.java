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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenAIService openAIService = retrofit.create(OpenAIService.class);

        sendButton.setOnClickListener(view -> {
            String userInput = userInputEditText.getText().toString().trim();
            if (!userInput.isEmpty()) {
                addMessageToChat(userInput, true); // Display user message
                userInputEditText.setText(""); // Clear input field

                List<OpenAIRequest.Message> messages = new ArrayList<>();
                messages.add(new OpenAIRequest.Message("user", userInput));
                OpenAIRequest request = new OpenAIRequest("gpt-4", messages); // Assuming "gpt-4" is your intended model

                // Adjust 'YOUR_OPENAI_API_KEY' with your actual API key
                String authHeader = "Bearer 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";

                // Make the API call
                openAIService.createChatCompletion(authHeader, request).enqueue(new Callback<OpenAIResponse>() {
                    @Override
                    public void onResponse(Call<OpenAIResponse> call, Response<OpenAIResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getChoices() != null && !response.body().getChoices().isEmpty()) {
                            OpenAIResponse.Choice firstChoice = response.body().getChoices().get(0);
                            if (firstChoice.getMessage() != null) {
                                String botResponse = firstChoice.getMessage().getContent();
                                addMessageToChat(botResponse, false); // Display bot response
                            }
                        } else {
                            addMessageToChat("Failed to get a response.", false);
                        }
                    }

                    @Override
                    public void onFailure(Call<OpenAIResponse> call, Throwable t) {
                        addMessageToChat("API call failed: " + t.getMessage(), false);
                    }
                });
            }
        });
    }

    private void addMessageToChat(String message, boolean isUserMessage) {
        TextView messageView = new TextView(this);
        messageView.setText(message);
        messageView.setPadding(16, 16, 16, 16);
        if (isUserMessage) {
            messageView.setBackground(getResources().getDrawable(R.drawable.user_message_background));
            messageView.setTextColor(getResources().getColor(android.R.color.black));
        } else {
            messageView.setBackground(getResources().getDrawable(R.drawable.api_response_background));
            messageView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        chatContainer.addView(messageView);

        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
