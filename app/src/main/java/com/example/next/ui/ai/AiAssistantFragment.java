package com.example.next.ui.ai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.next.databinding.FragmentChatBotBinding;
public class AiAssistantFragment extends Fragment {

    private FragmentChatBotBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Example of setting dynamic text to textView
        binding.textNotifications.setText("Welcome to AI Assistant!");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
