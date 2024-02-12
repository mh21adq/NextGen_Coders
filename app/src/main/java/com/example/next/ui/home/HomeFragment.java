package com.example.next.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.next.R;
import com.example.next.databinding.FragmentHomeBinding;
import com.example.next.LearnJavaActivity; // Import the LearnJavaActivity

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the button using the ID from your layout file
        Button learnJavaButton = root.findViewById(R.id.button_java);

        // Set an OnClickListener for the button
        learnJavaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start LearnJavaActivity
                Intent intent = new Intent(getActivity(), LearnJavaActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
