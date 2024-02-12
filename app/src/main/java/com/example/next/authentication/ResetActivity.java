package com.example.next.authentication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.next.R;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        auth = FirebaseAuth.getInstance();
        EditText editTextEmail = findViewById(R.id.email);
        Button buttonResetPassword = findViewById(R.id.confirm);
        Button buttonGoToSignIn = findViewById(R.id.remembered_pass);

        buttonResetPassword.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(ResetActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                        }
                    });
        });


        buttonGoToSignIn.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}
