package com.example.next.ui.examples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.next.R;
import com.example.next.databinding.FragmentExamplesBinding;

public class ExamplesFragment extends Fragment {

    private FragmentExamplesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExamplesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Access the TextView from the inflated layout
        TextView textView = binding.getRoot().findViewById(R.id.textView);

        // Set the text to "Hello, World!"
        textView.setText("Hello, World!");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Clean up binding when view is destroyed
    }
}
