package com.example.next.ui.examples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.next.R;

public class ExamplesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_examples, container, false);

        // Set up each TextView with click listeners
        TextView textViewHelloWorld = root.findViewById(R.id.example_item_1);
        textViewHelloWorld.setOnClickListener(v -> {
            // Handle the click event for "Hello World"
            logMessage("Hello World clicked");
        });

        TextView textViewAddTwoIntegers = root.findViewById(R.id.example_item_2);
        textViewAddTwoIntegers.setOnClickListener(v -> {
            // Handle the click event for "Add Two Integers"
            logMessage("Add Two Integers clicked");
        });

        TextView textViewMultiplyFloats = root.findViewById(R.id.example_item_3);
        textViewMultiplyFloats.setOnClickListener(v -> {
            // Handle the click event for "Multiply two Floating-Point Numbers"
            logMessage("Multiply two Floating-Point Numbers clicked");
        });

        TextView textViewComputeQuotientAndRemainder = root.findViewById(R.id.example_item_4);
        textViewComputeQuotientAndRemainder.setOnClickListener(v -> {
            // Handle the click event for "Compute Quotient and Remainder"
            logMessage("Compute Quotient and Remainder clicked");
        });



        return root;
    }

    private void logMessage(String message) {
        System.out.println("ExamplesFragment: " + message);
    }
}