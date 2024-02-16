package com.example.next.ui.ai;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.next.databinding.FragmentChatBotBinding;

public class AiAssistantFragment extends Fragment {

    private FragmentChatBotBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set the click listener for the "Open ChatGPT" button
        binding.openChatGPTButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatGPTActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
