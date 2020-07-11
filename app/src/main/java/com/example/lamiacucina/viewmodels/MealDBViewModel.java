package com.example.lamiacucina.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.repositories.MealsRepository;

import java.util.List;

public class MealDBViewModel extends AndroidViewModel {
    private MealsRepository mealsRepository;
    private LiveData<List<Meal>> meals;

    public MealDBViewModel(@NonNull Application application) {
        super(application);
        mealsRepository = new MealsRepository((application));
        meals = mealsRepository.getAllMeals();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return meals;
    }

    public void insertIMeal(Meal meal) {
        mealsRepository.insert(meal);
    }

    public void deleteMeal(Meal meal) {
        mealsRepository.delete(meal);
    }


}
