package com.example.next.ui.compiler;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import com.example.next.R;

import java.io.IOException;

public class CompilerFragment extends Fragment {

    private EditText codeEditor;
    private TextView outputTextView;
    private TextView lineNumbers; // TextView for line numbers
    private Button runButton;
    private static final String JDOODLE_API_URL = "https://api.jdoodle.com/v1/execute";
    private static final String JDOODLE_CLIENT_ID = "8ee54aa27918202568f37c483205e0e5";
    private static final String JDOODLE_CLIENT_SECRET = "7242340bdda9b18b1ed0993d48e7b1eb62cd70f2e5c40b6f031a3efa369b4dec";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_compiler, container, false);

        codeEditor = root.findViewById(R.id.codeEditor);
        outputTextView = root.findViewById(R.id.outputTextView);
        lineNumbers = root.findViewById(R.id.lineNumbers); // Initialize the line numbers TextView
        runButton = root.findViewById(R.id.runButton);

        codeEditor.setText("public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}");

        codeEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleBracketInsertion(s, start, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateLineNumbers();
            }
        });

        codeEditor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    handleEnterKeyPress();
                    return true;
                }
                return false;
            }
        });

        runButton.setOnClickListener(view -> executeCode(codeEditor.getText().toString()));

        updateLineNumbers(); // Initial line numbers update

        return root;
    }

    private void handleBracketInsertion(CharSequence s, int start, int count) {
        if (count == 1) {
            char newChar = s.charAt(start);
            char closingChar = getClosingChar(newChar);
            if (closingChar != '\0') {
                codeEditor.getText().insert(start + 1, String.valueOf(closingChar));
                codeEditor.setSelection(start + 1);
            }
        }
    }

    private char getClosingChar(char openingChar) {
        switch (openingChar) {
            case '(': return ')';
            case '{': return '}';
            default: return '\0'; // No matching bracket
        }
    }

    private void handleEnterKeyPress() {
        int start = codeEditor.getSelectionStart();
        int end = codeEditor.getSelectionEnd();
        String text = codeEditor.getText().toString();

        int line = codeEditor.getLayout().getLineForOffset(start);
        int lineStart = codeEditor.getLayout().getLineStart(line);
        int lineEnd = start - 1; // Adjust to get the end of the previous line

        String lineContent = text.substring(lineStart, lineEnd);
        String indentation = getIndentation(lineContent);

        codeEditor.getText().replace(Math.min(start, end), Math.max(start, end),
                "\n" + indentation, 0, indentation.length() + 1);
    }

    private String getIndentation(String lineContent) {
        StringBuilder indent = new StringBuilder();
        for (char c : lineContent.toCharArray()) {
            if (c == ' ' || c == '\t') {
                indent.append(c);
            } else {
                break;
            }
        }
        return indent.toString();
    }

    private void updateLineNumbers() {
        int linesCount = codeEditor.getLineCount();
        StringBuilder lines = new StringBuilder();
        for (int i = 1; i <= linesCount; i++) {
            lines.append(i).append("\n");
        }
        lineNumbers.setText(lines.toString());
    }

    private void executeCode(String code) {
        OkHttpClient client = new OkHttpClient();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("clientId", JDOODLE_CLIENT_ID);
        jsonObject.addProperty("clientSecret", JDOODLE_CLIENT_SECRET);
        jsonObject.addProperty("script", code);
        jsonObject.addProperty("language", "java");
        jsonObject.addProperty("versionIndex", "0");

        RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder().url(JDOODLE_API_URL).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> outputTextView.setText("Failed to compile the code."));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    JsonObject responseJson = JsonParser.parseString(responseData).getAsJsonObject();
                    final String output = responseJson.get("output").getAsString();
                    getActivity().runOnUiThread(() -> outputTextView.setText(output));
                } else {
                    getActivity().runOnUiThread(() -> outputTextView.setText("Error occurred while trying to compile the code."));
                }
            }
        });
    }
}
