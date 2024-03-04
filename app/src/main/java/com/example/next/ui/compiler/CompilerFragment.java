package com.example.next.ui.compiler;

import android.os.Bundle;
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
    private Button runButton;
    private static final String JDOODLE_API_URL = "https://api.jdoodle.com/v1/execute";
    private static final String JDOODLE_CLIENT_ID = "8ee54aa27918202568f37c483205e0e5";  // Use your actual JDoodle client ID
    private static final String JDOODLE_CLIENT_SECRET = "7242340bdda9b18b1ed0993d48e7b1eb62cd70f2e5c40b6f031a3efa369b4dec";  // Use your actual JDoodle client secret

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_compiler, container, false);

        codeEditor = root.findViewById(R.id.codeEditor);
        outputTextView = root.findViewById(R.id.outputTextView);
        runButton = root.findViewById(R.id.runButton);

        // Initialize the code editor with a default "Hello World" Java program
        String defaultHelloWorldCode = "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}";
        codeEditor.setText(defaultHelloWorldCode);

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = codeEditor.getText().toString();
                executeCode(code);
            }
        });

        return root;
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

        Request request = new Request.Builder()
                .url(JDOODLE_API_URL)
                .post(requestBody)
                .build();

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
