package com.example.next.ViewModel.Firebase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.next.View.NavigationActivity;
import com.example.next.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * LoginActivity handles user login activities including authenticating with Firebase,
 * navigating to the registration page, and password reset functionalities.
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * EditText for user to enter their email.
     */
    private EditText editTextEmail;

    /**
     * EditText for user to enter their password.
     */
    private EditText editTextPassword;

    /**
     * Button for initiating the login process.
     */
    private Button login;

    /**
     * Button to navigate to the sign-up activity.
     */
    private Button signUp;

    /**
     * TextView for navigating to the password reset activity.
     */
    private TextView forgetPassword;

    /**
     * Instance of FirebaseAuth for handling authentication.
     */
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.create_account);
        forgetPassword = findViewById(R.id.forget);

        // Navigate to RegisterActivity when signUp is clicked
        signUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });

        // Navigate to ResetActivity when forgetPassword is clicked
        forgetPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ResetActivity.class));
        });

        // Attempt to login when login button is clicked
        login.setOnClickListener(view -> {
            if (isInputValid()) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Authenticate with Firebase
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Login successful
                                Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                            } else {
                                // Login failed
                                Toast.makeText(LoginActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

    /**
     * Validates the email and password input by the user.
     *
     * @return true if the input is valid, false otherwise.
     */
    private boolean isInputValid() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required.");
            editTextEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required.");
            editTextPassword.requestFocus();
            return false;
        }

        return true; // Both inputs are valid
    }
}
