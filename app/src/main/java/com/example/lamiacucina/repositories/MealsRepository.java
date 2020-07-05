package com.example.lamiacucina.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.models.Resource;
import com.example.lamiacucina.models.SearchByNameApiResponse;
import com.example.lamiacucina.services.MealsService;
import com.example.lamiacucina.utils.Constants;

import java.io.IOException;
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

    public void getMealsByName(MutableLiveData<Resource<List<Meal>>> mealsResource, String s){
        Call<SearchByNameApiResponse> call =  mealsService.getMealsByName(s, Constants.MEALS_API_KEY);

        call.enqueue(new Callback<SearchByNameApiResponse>() {
            @Override
            public void onResponse(Call<SearchByNameApiResponse> call, Response<SearchByNameApiResponse> response) {
                if (response.isSuccessful() && response.body().getMeals() != null) {

                    Resource<List<Meal>> resource = new Resource<>();

                    resource.setData(response.body().getMeals());
                    resource.setTotalResults(response.body().getMeals().size());
                    resource.setStatusCode(response.code());
                    resource.setStatusMessage(response.message());
                    mealsResource.postValue(resource);

                }else if(response.errorBody() != null){

                    Resource<List<Meal>> resource = new Resource<>();
                    resource.setStatusCode(response.code());
                    try {
                        resource.setStatusMessage(response.errorBody().string() + " " + response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mealsResource.postValue(resource);
                }
            }

            @Override
            public void onFailure(Call<SearchByNameApiResponse> call, Throwable t) {

                Resource<List<Meal>> resource = new Resource<>();
                resource.setStatusMessage(t.getMessage());

                mealsResource.postValue(resource);

            }
        });




    }

    }
