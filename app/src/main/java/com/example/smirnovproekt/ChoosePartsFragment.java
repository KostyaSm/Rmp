package com.example.meal_builder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meal_builder.databinding.MealPartTemplateBinding;
import com.example.meal_builder.databinding.MealPartVariantBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class ChoosePartsFragment extends Fragment {
    static ArrayList<ChoosableMealPart> choosableParts = new ArrayList<ChoosableMealPart>(){
        {
            add(new ChoosableMealPart(123, 421, 333, 44, "Сыр", "salad"));
            add(new ChoosableMealPart(123, 421, 333, 44, "Колбаса", "tomatoes"));
            for (int i = 0; i < 200; i++) {
                add(new ChoosableMealPart(123, 421, 333, 44, "Сыр", "salad"));
            }
        }
    };

    private final String TAG = this.getClass().getSimpleName();
    ArrayList<ChoosableMealPart> chosenParts = new ArrayList<ChoosableMealPart>();
    ChosenPartsService partsService = new ChosenPartsService();

    public ChoosePartsFragment() {
        super(R.layout.fragment_choose_parts);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        Toast.makeText(getContext(), "onViewCreated", Toast.LENGTH_SHORT).show();

        RecyclerView partsList = getView().findViewById(R.id.part_variants_container);
        MealPartRecyclerAdapter adapter = new MealPartRecyclerAdapter(getContext(), choosableParts, partsService);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        partsList.setLayoutManager(manager);
        partsList.setAdapter(adapter);
        Button cancelBtn = (Button) getView().findViewById(R.id.chooseCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.chooseSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            Bundle result = new Bundle();
            result.putSerializable("parts", partsService.getParts());
//            putStringArrayList("parts", new ArrayList<String>(Arrays.asList("asd", "123")));
            getParentFragmentManager().setFragmentResult("partsChoise", result);
            getParentFragmentManager.beginTransaction().remove(this).commit();
        });

        public void addChosenPart(ChoosableMealPart part) {
            this.chosenParts.add(part);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
        Toast.makeText(context, "onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Log.i(TAG, "onViewStateRestored");
        Toast.makeText(getContext(), "onViewStateRestored", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
    }
}