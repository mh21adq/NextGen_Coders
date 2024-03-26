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

/**
 * A {@link Fragment} subclass that displays examples to the user.
 * Each example, when clicked, navigates to a detailed code display activity.
 */
public class ExamplesFragment extends Fragment {

    /**
     * Inflates the layout for this fragment, setting up click listeners on example items
     * to navigate to their respective code display activities.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return Returns the View for the fragment's UI.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_examples, container, false);

        // Find the TextView for the "Hello World" example and set a click listener
        TextView textViewHelloWorld = root.findViewById(R.id.example_item_1);
        textViewHelloWorld.setOnClickListener(v -> navigateToCodeDisplay("hello_world"));

        // Find the TextView for the "Add Two Integers" example and set a click listener
        TextView textViewAddTwoIntegers = root.findViewById(R.id.example_item_2);
        textViewAddTwoIntegers.setOnClickListener(v -> navigateToCodeDisplay("add_two_integers"));

        return root;
    }

    /**
     * Initiates navigation to the CodeDisplayActivity, passing the name of the example to display.
     *
     * @param exampleName The name of the example to display in the CodeDisplayActivity.
     */
    private void navigateToCodeDisplay(String exampleName) {
        Intent intent = new Intent(getActivity(), CodeDisplayActivity.class);
        intent.putExtra("example_name", exampleName);
        startActivity(intent);
    }
}
