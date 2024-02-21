package com.example.next.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.next.LearnJavaActivity; // Import the LearnJavaActivity
import com.example.next.R;
import com.example.next.databinding.FragmentHomeBinding;
import com.example.next.ui.ai.ChatGPTActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
