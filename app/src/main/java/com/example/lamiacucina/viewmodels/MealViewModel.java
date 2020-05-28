package com.example.lamiacucina.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.models.Resource;
import com.example.lamiacucina.repositories.MealsRepository;

import java.util.List;

public class MealViewModel extends ViewModel {

    private MutableLiveData<Resource<List<Meal>>> meals;

    public MutableLiveData<Resource<List<Meal>>> getMealsResource(String s) {
        //if (meals == null) { // sigleton!!!
            meals = new MutableLiveData<>();
            MealsRepository.getInstance().getMealsByName(meals, s);

        //}
        return meals;
    }


}
