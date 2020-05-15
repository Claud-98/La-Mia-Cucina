package com.example.lamiacucina.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.repositories.MealsRepository;

import java.util.List;

public class MealViewModel extends ViewModel {

    private MutableLiveData<List<Meal>> meals;

    public MutableLiveData<List<Meal>> getMeals(String s) {
        //if (meals == null) { // sigleton!!!
            meals = new MutableLiveData<>();
            MealsRepository.getInstance().getMealsByName(meals, s);

        //}
        return meals;
    }


}
