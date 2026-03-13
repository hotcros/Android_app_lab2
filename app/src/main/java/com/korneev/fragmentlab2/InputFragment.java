package com.korneev.fragmentlab2;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
public class InputFragment extends Fragment {
    private SharedViewModel viewModel;
    private EditText editText;
    private RadioGroup radioGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        editText = view.findViewById(R.id.edit_text_input);
        radioGroup = view.findViewById(R.id.radio_group_colors);
        Button btnOk = view.findViewById(R.id.button_ok);
        btnOk.setOnClickListener(v -> {
            String inputText = editText.getText().toString().trim();
            int checkedId = radioGroup.getCheckedRadioButtonId();
            if (inputText.isEmpty() || checkedId == -1) {
                Toast.makeText(requireContext(), "Будь ласка, введіть текст та оберіть колір!", Toast.LENGTH_SHORT).show();
            } else {
                int selectedColor = Color.BLACK;
                if (checkedId == R.id.radio_red) selectedColor = Color.RED;
                else if (checkedId == R.id.radio_green) selectedColor = Color.GREEN;
                else if (checkedId == R.id.radio_blue) selectedColor = Color.BLUE;
                viewModel.setData(inputText, selectedColor);
            }
        });
        viewModel.getClearTrigger().observe(getViewLifecycleOwner(), clear -> {
            if (clear) {
                editText.setText("");
                radioGroup.clearCheck();
            }
        });
        return view;
    }
}