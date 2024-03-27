package com.example.next.ViewModel.Firebase;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.next.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * ResetActivity facilitates password reset functionality for users.
 */
public class ResetActivity extends AppCompatActivity {

    /**
     * FirebaseAuth instance for authentication tasks.
     */
    private FirebaseAuth auth;

    /**
     * Initializes the activity's UI and sets up event listeners.
     * @param savedInstanceState Contains data supplied in onSaveInstanceState(Bundle) if the activity is re-initialized after previously being shut down. Otherwise, it's null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        // Initialize Firebase authentication
        auth = FirebaseAuth.getInstance();

        // UI elements: email input field and buttons for actions
        EditText editTextEmail = findViewById(R.id.email);
        Button buttonResetPassword = findViewById(R.id.confirm);
        Button buttonGoToSignIn = findViewById(R.id.remembered_pass);

        // Listener for the password reset button
        buttonResetPassword.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(ResetActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Attempt to send a password reset email
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Listener for the button that allows users to go back to the sign-in screen
        buttonGoToSignIn.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}
