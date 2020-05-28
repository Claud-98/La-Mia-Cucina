package com.example.lamiacucina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lamiacucina.databinding.FragmentMealDetailsBinding;
import com.example.lamiacucina.databinding.FragmentShowMealBinding;
import com.example.lamiacucina.models.Meal;


/**
 * A simple {@link Fragment} subclass.
 */
public class MealDetailsFragment extends Fragment {

    private FragmentMealDetailsBinding binding;

    public MealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMealDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //
        MenuItem item = menu.getItem(0);
        item.setVisible(false);


        inflater.inflate(R.menu.show_meal_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Meal meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMeal();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(meal.getStrMeal());

        binding.recipeDirections.setText(meal.getStrInstructions());





    }
}
