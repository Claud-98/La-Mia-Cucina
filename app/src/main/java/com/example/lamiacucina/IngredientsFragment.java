package com.example.lamiacucina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.lamiacucina.databinding.FragmentIngredientsBinding;
import com.example.lamiacucina.models.Meal;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {
    private FragmentIngredientsBinding binding;
    private String INGREDIENTS = "Ingredients";
    private ArrayList <String> ingredients;
    private Meal meal;

    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meal = (Meal) getArguments().getParcelable(INGREDIENTS);
        ingredients = meal.ingredientsToList();
        Log.d("TAG", "Ciao" + ingredients.size());

        LinearLayout ll = getView().findViewById(R.id.ingredientsLl) ;
        for (int i=0; i<ingredients.size();i ++){
            CheckBox checkBox = new CheckBox(view.getContext());
            checkBox.setId(i);
            checkBox.setText(ingredients.get(i));
            ll.addView(checkBox);
        }




    }
}
