package com.example.next.ViewModel.ChatGPT;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.next.Model.OpenAI.OpenAIRequest;
import com.example.next.Model.OpenAI.OpenAIResponse;
import com.example.next.Model.OpenAI.OpenAIService;
import com.example.next.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ChatGPTActivity is responsible for providing a user interface where users can interact with
 * an AI model via text. It handles sending user inputs to the AI and displaying its responses.
 */
public class ChatGPTActivity extends AppCompatActivity {

    private LinearLayout chatContainer;
    private EditText userInputEditText;
    private ImageButton sendButton;

    /**
     * Sets up the activity's interface on creation, initializes interaction components, and
     * prepares the network request setup for communicating with OpenAI's GPT model.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise it is null.
     */
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
                OpenAIRequest request = new OpenAIRequest("gpt-4", messages);

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

    /**
     * Adds a message to the chat container, visually distinguishing between user messages and AI responses.
     *
     * @param message The message to be displayed.
     * @param isUserMessage A flag indicating whether the message is from the user (true) or from the AI (false).
     */
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

    /**
     * Overrides the action bar's Up button to mimic the back button behavior.
     *
     * @return true to indicate that the Up navigation completed successfully.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
