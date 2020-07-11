package com.example.lamiacucina.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lamiacucina.models.Meal;

import java.util.List;
@Dao
public interface MealDao {



    @Insert
    void insertItem(Meal meal);


    @Delete
    void deleteItem(Meal meal);



    @Query("SELECT * FROM meals")
    LiveData<List<Meal>> getAllMeal();



}
