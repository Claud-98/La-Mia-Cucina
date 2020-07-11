package com.example.lamiacucina;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lamiacucina.adapters.SearchMealAdapter;
import com.example.lamiacucina.databinding.ActivityMainBinding;

import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.models.Resource;
import com.example.lamiacucina.viewmodels.MealDBViewModel;
import com.example.lamiacucina.viewmodels.MealViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SearchMealAdapter searchMealAdapter;
    private MealDBViewModel mealDBViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        androidx.appcompat.widget.Toolbar toolbar;
        toolbar = binding.mainActivityToolbar;
        setSupportActionBar(toolbar);
        toolbar.setTitle("@string/app_title");
        /*
        if(savedInstanceState != null){
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        }
        */

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.showMealRecyclerView.setLayoutManager(layoutManager);

        //mealDBViewModel = new ViewModelProvider(this).get(MealDBViewModel.class);




    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        final MenuItem searchItem = menu.findItem(R.id.Search_MA);

        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent search = new Intent(MainActivity.this, SearchActivity.class);
                search.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(search);
                return false;
            }
        });

        return true;
    }



    /*
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SEARCH_QUERY, searchQuery);
        super.onSaveInstanceState(outState);

    }
    */

}
