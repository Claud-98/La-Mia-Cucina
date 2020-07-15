package com.example.lamiacucina.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lamiacucina.models.Meal;

@Database(entities = {Meal.class}, version = 1, exportSchema = false)
public abstract class MealsRoomDB extends RoomDatabase {

    private static MealsRoomDB INSTANCE;

    public abstract MealDao dataDAO();

    public static MealsRoomDB getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MealsRoomDB.class, MealsRoomDB.class.getName()).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}


