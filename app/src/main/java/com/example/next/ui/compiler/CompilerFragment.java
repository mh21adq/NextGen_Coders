package com.example.next.ui.compiler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.next.databinding.FragmentCompilerBinding;

public class CompilerFragment extends Fragment {

    private FragmentCompilerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCompilerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setup the Spinner for compiler options
        Spinner compilerSpinner = binding.compilerSpinner;
        // Example compiler options
        String[] compilerOptions = {"Java", "C", "Python"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, compilerOptions);
        compilerSpinner.setAdapter(adapter);

        // Setup the EditText for code input


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
