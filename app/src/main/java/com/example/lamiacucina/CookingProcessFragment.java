package com.example.lamiacucina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lamiacucina.databinding.FragmentCookingProcessBinding;
import com.example.lamiacucina.models.Meal;


public class CookingProcessFragment extends Fragment {

    FragmentCookingProcessBinding binding;

    public CookingProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCookingProcessBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Meal meal = CookingProcessFragmentArgs.fromBundle(getArguments()).getMeal();
        //binding.cookingProcessText.setText(meal.getStrIngredient1());

    }
}
