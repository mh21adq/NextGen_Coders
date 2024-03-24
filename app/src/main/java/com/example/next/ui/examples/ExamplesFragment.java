package com.example.next.ui.examples;

import android.content.Intent;
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

        TextView textViewHelloWorld = root.findViewById(R.id.example_item_1);
        textViewHelloWorld.setOnClickListener(v -> navigateToCodeDisplay("hello_world"));

        TextView textViewAddTwoIntegers = root.findViewById(R.id.example_item_2);
        textViewAddTwoIntegers.setOnClickListener(v -> navigateToCodeDisplay("add_two_integers"));

        return root;
    }

    private void navigateToCodeDisplay(String exampleName) {
        Intent intent = new Intent(getActivity(), CodeDisplayActivity.class);
        intent.putExtra("example_name", exampleName);
        startActivity(intent);
    }
}
