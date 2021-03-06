package com.example.lamiacucina.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SearchByNameApiResponse{
    private List<Meal> meals;

    public SearchByNameApiResponse(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "SearchByNameApiResponse{" +
                "meals=" + meals +
                '}';
    }



}
