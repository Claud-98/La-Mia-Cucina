package com.example.lamiacucina.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.models.SearchByNameApiResponse;
import com.example.lamiacucina.services.MealsService;
import com.example.lamiacucina.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRepository {

    private static MealsRepository instance;
    private MealsService mealsService;

    private MealsRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MEALS_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        mealsService = retrofit.create(MealsService.class);
    }

    public static synchronized   MealsRepository getInstance() {
        if(instance == null){
             instance = new MealsRepository();
        }
        return instance;
    }

    public void getMealsByName(MutableLiveData<List<Meal>> meals, String s){
        Call<SearchByNameApiResponse> call =  mealsService.getMealsByName(s, Constants.MEALS_API_KEY);

        call.enqueue(new Callback<SearchByNameApiResponse>() {
            @Override
            public void onResponse(Call<SearchByNameApiResponse> call, Response<SearchByNameApiResponse> response) {

                meals.postValue(response.body().getMeals());

            }

            @Override
            public void onFailure(Call<SearchByNameApiResponse> call, Throwable t) {

            }
        });


    }

    }
