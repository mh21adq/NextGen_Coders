package com.example.next.ui.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.next.authentication.LoginActivity;
import com.example.next.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean nightMode;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize UI components and set up event listeners
        initializeUI();

        return root;
    }

    private void initializeUI() {
        binding.accountSettings.setOnClickListener(view -> showToast("Account Settings clicked"));
        binding.about.setOnClickListener(view -> showToast("About clicked"));
        binding.logout.setOnClickListener(view -> navigateToLogin());

        // Setup dark mode toggle switch
        setupDarkModeSwitch();
    }

    private void setupDarkModeSwitch() {
        sharedPreferences = requireActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false); // Default value is false

        // Set the switch state based on the saved preference
        binding.darkModeSwitch.setChecked(nightMode);

        // Set the default night mode based on the saved preference
        AppCompatDelegate.setDefaultNightMode(nightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        // Listen for switch changes and apply theme accordingly
        binding.darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d("DarkModeSwitch", "Switch state changed: " + isChecked);
            editor = sharedPreferences.edit();
            editor.putBoolean("nightMode", isChecked);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

            // Recreate the activity for changes to take effect
            requireActivity().recreate();
        });
    }


    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
