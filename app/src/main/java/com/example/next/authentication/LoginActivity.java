package com.example.next.authentication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.next.NavigationActivity;
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

    /**
     * Called when the activity is starting. This is where most initialization should go:
     * calling setContentView(int) to inflate the activity's UI, using findViewById(int) to programmatically
     * interact with widgets in the UI, registering listeners, and using getIntent() to retrieve any data
     * passed to this activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
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
            startActivity(new Intent(
                    LoginActivity.this,
                    RegisterActivity.class));
            finish();
        });

        // Navigate to ResetActivity when forgetPassword is clicked
        forgetPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ResetActivity.class));
        });

        // Attempt to login when login button is clicked
        login.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Validate input
            if (email.isEmpty()) {
                editTextEmail.setError("Email is required.");
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError("Password is required.");
                return;
            }

            // Authenticate with Firebase
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Login successful
                            Toast.makeText(LoginActivity.this,
                                    "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                        } else {
                            // Login failed
                            Toast.makeText(LoginActivity.this, "Login Failed: "
                                    + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

    }

}
