package com.example.next.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.next.lessons.learnJava.LearnJavaActivity; // Import the LearnJavaActivity
import com.example.next.R;
import com.example.next.databinding.FragmentHomeBinding;

/**
 * HomeFragment serves as the home screen fragment where users can select different programming language lessons,
 * including an option to learn Java.
 */
public class HomeFragment extends Fragment {

    /**
     * Binding instance for accessing the layout views.
     */
    private FragmentHomeBinding binding;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return Return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Finds the CardView for the Java learning option and sets an OnClickListener to launch LearnJavaActivity.
        CardView learnJavaImageView = root.findViewById(R.id.button_java);
        learnJavaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeFragment", "Java button clicked");
                try {
                    Intent intent = new Intent(getActivity(), LearnJavaActivity.class);
                    startActivity(intent);
                    Log.d("HomeFragment", "LearnJavaActivity started");
                } catch (Exception e) {
                    Log.e("HomeFragment", "Error starting LearnJavaActivity", e);
                }
            }
        });

        return root;
    }

    /**
     * Called when the view previously created by onCreateView has been detached from the fragment.
     * The binding is nullified here to prevent memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
