package com.example.next.ui.ai;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.next.databinding.FragmentChatBotBinding;

/**
 * AiAssistantFragment hosts the user interface for interacting with an AI chat feature, such as ChatGPT.
 */
public class AiAssistantFragment extends Fragment {

    private FragmentChatBotBinding binding;

    /**
     * Inflates the layout for the AI Assistant fragment and sets up the interaction logic.
     * @param inflater LayoutInflater object to inflate views in the fragment.
     * @param container If non-null, is the parent view to which the fragment's UI should be attached.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the inflated fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the "Open ChatGPT" button with a click listener to open the ChatGPTActivity.
        binding.openChatGPTButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatGPTActivity.class);
            startActivity(intent);
        });

        return root;
    }

    /**
     * Cleans up resources and nullifies the binding when the view is destroyed.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to prevent memory leaks.
        binding = null;
    }
}
