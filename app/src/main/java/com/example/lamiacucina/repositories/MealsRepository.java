package com.example.lamiacucina.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lamiacucina.database.MealDao;
import com.example.lamiacucina.database.MealsRoomDB;
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
    //DB
    private MealDao mealDao;
    private LiveData<List<Meal>> allMeals;
    //




    private MealsRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MEALS_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        mealsService = retrofit.create(MealsService.class);
    }

    //DB
    public MealsRepository(Application application) {
        MealsRoomDB mealRoomDB = MealsRoomDB.getDatabase(application);
        this.mealDao = mealRoomDB.dataDAO();
        this.allMeals = mealDao.getAllMeal();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }
    //



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
// DB operations
    public void insert(Meal meal) {
        new insertAsyncTask(mealDao).execute(meal);
    }

    private static class insertAsyncTask extends AsyncTask<Meal, Void, Void> {
        private MealDao mAsyncTaskDao;
        insertAsyncTask(MealDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.insertItem(params[0]);
            return null;
        }
    }

    public void delete(Meal meal) {
        new deleteAsyncTask(mealDao).execute(meal);
    }

    private static class deleteAsyncTask extends AsyncTask<Meal, Void, Void> {
        private MealDao asyncTaskDao;
        deleteAsyncTask(MealDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            asyncTaskDao.deleteItem(params[0]);
            return null;
        }



    /*

    public void deleteItemById(Long idMeal) {
        new deleteByIdAsyncTask(mealDao).execute(idMeal);
    }

    private static class deleteByIdAsyncTask extends AsyncTask<Long, Void, Void> {
        private MealDao mAsyncTaskDao;
        deleteByIdAsyncTask(MealDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            mAsyncTaskDao.deleteByMealId(params[0]);
            return null;
        }
*/
//
    }


}




