package com.example.meal_builder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealPartVariantAdapter extends ArrayAdapter<MealPart> {
    private final int layout;
    private LayoutInflater inflater;

    private final String TAG = this.getClass().getSimpleName();

    public MealPartVariantAdapter(Context context, int resource, List<MealPart> items) {
        super(context, R.layout.meal_part_template, items);
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView title;
        TextView calories;
        TextView fats;
        TextView protein;
        TextView carbohydrates;
        EditText grams;
        ImageView image;
        TextView totalCalories;
        TextView totalFats;
        TextView totalProtein;
        TextView totalCarbohydrates;

        LinearLayout partsLayout;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = getContext();
        MealPart item = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(this.layout, parent, false);

            holder.title = convertView.findViewById(R.id.part_title);
            holder.calories = convertView.findViewById(R.id.part_calories);
            holder.fats = convertView.findViewById(R.id.part_fats);
            holder.protein = convertView.findViewById(R.id.part_protein);
            holder.carbohydrates = convertView.findViewById(R.id.part_carbohydrates);
            holder.grams = convertView.findViewById(R.id.part_grams);
            holder.image = convertView.findViewById(R.id.part_image);
            holder.totalCalories = convertView.findViewById(R.id.part_total_calories);
            holder.totalFats = convertView.findViewById(R.id.part_total_fats);
            holder.totalProtein = convertView.findViewById(R.id.part_total_protein);
            holder.totalCarbohydrates = convertView.findViewById(R.id.part_total_carbohydrates);

            holder.partsLayout = convertView.findViewById(R.id.parts_layout);
            holder.partsLayout.setOnClickListener((layout) -> {
                Log.i(TAG, "ItemClicked!");
                Toast.makeText(getContext(), "ItemClicked!", Toast.LENGTH_SHORT).show();
            });
            holder.grams.setText(String.valueOf(item.grams));
            holder.grams.setId(position);
            holder.grams.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    final int position1 = v.getId();
                    final EditText gramsView = (EditText) v;
                    getItem(position1).grams = Integer.parseInt(gramsView.getText().toString());
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        calories.setText(String.valueOf(item.calories));
        fats.setText(String.valueOf(item.fats));
        protein.setText(String.valueOf(item.protein));
        carbohydrates.setText(String.valueOf(item.carbohydrates));

        grams.setText(String.valueOf(item.grams));

        String uri = "@drawable/" + item.image;
        image.setImageResource(context.getResources().getIdentifier(uri, null, context.getPackageName()));

        totalCalories.setText(String.valueOf(item.getTotalCalories()));
        totalFats.setText(String.valueOf(item.getTotalFats()));
        totalProtein.setText(String.valueOf(item.getTotalProtein()));
        totalCarbohydrates.setText(String.valueOf(item.getTotalCarbohydrates()));

        return view;
    }
}