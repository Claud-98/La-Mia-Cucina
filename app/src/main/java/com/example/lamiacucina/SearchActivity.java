package com.example.lamiacucina;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lamiacucina.adapters.SearchMealAdapter;
import com.example.lamiacucina.databinding.ActivitySearchBinding;
import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.viewmodels.MealViewModel;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import java.util.List;


public class SearchActivity extends AppCompatActivity {
    private String TAG = "SearchActivity";
    private ActivitySearchBinding binding;
    private MealViewModel mealViewModel;
    private SearchMealAdapter searchMealAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //NavController navController = Navigation.findNavController(this, R.id.navHostfragment);


        androidx.appcompat.widget.Toolbar toolbar;
        toolbar = binding.searchActivityToolbar;
        setSupportActionBar(toolbar);

        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);


        mealViewModel.getAllMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealList) {

            }
        });









    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;

    }


}
