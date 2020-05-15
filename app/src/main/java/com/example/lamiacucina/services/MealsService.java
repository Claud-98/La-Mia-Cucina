package com.example.lamiacucina.services;

import com.example.lamiacucina.models.SearchByNameApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MealsService {
    @GET("search.php")
    Call<SearchByNameApiResponse> getMealsByName(@Query("s") String s, @Header("Authorization") String apiKey);
}
