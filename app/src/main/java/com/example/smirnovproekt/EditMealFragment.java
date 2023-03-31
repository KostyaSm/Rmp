package com.example.meal_builder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.meal_builder.databinding.MealPartTemplateBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class EditMealFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public EditMealFragment() {
        super(R.layout.fragment_edit_meal);
    }

    static ArrayList<MealPart> parts = new ArrayList<MealPart>() {
        {
            add(new MealPart(ChoosePartsFragment.choosableParts.get(0)));
            add(new MealPart(ChoosePartsFragment.choosableParts.get(1)));
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String title = requireArguments().getString("title");


        TextView titleView = (TextView) getView().findViewById(R.id.meal_title);

        titleView.setText(title);

        ListView partsList = getView().findViewById(R.id.parts_container);
        partsList.setItemsCanFocus(true);
        MealPartVariantAdapter adapter = new MealPartVariantAdapter(getContext(), R.layout.meal_part_template, parts);
        partsList.setAdapter(adapter);


        Button cancelBtn = (Button) getView().findViewById(R.id.editCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            getParentFragmentManager().beginTransaction().remove(this).commit();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.editSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            Bundle result = new Bundle();
            result.putString("title", titleView.getText().toString());
            getParentFragmentManager().setFragmentResult("mealEdit", result);
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        Button chooseBtn = (Button) getView().findViewById(R.id.choosePartsBtn);
        chooseBtn.setOnClickListener((chooseBtn1) -> {
            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_choose, ChoosePartsFragment.class, new Bundle())
                    .commit();
        });

        getParentFragmentManager().setFragmentResultListener("partsChoise", this, (requestKey, result) -> {
            ArrayList<ChoosableMealPart> chosenParts = (ArrayList<ChoosableMealPart>) result.getSerializable("parts");
            for(ChoosableMealPart part : chosenParts) {
                parts.add(new MealPart(part));
            }

            adapter.notifyDataSetChanged();
        });
    }
}