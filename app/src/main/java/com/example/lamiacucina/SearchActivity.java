package com.example.lamiacucina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lamiacucina.databinding.ActivitySearchBinding;
import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.viewmodels.MealViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.acl.Owner;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private String TAG = "SearchActivity";
    private ActivitySearchBinding binding;
    private String searchQuery;
    private String SEARCH_QUERY = "searchQuery";
    private MealViewModel mealViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        androidx.appcompat.widget.Toolbar toolbar;
        toolbar = binding.searchActivityToolbar;

        setSupportActionBar(toolbar);
        setContentView(view);

        mealViewModel =new ViewModelProvider(this).get(MealViewModel.class);



        if(savedInstanceState != null){
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {


        getMenuInflater().inflate(R.menu.menu_search,menu);

        final MenuItem searchItem = menu.findItem(R.id.Search);

        searchItem.expandActionView();
        final SearchView searchView = (SearchView) searchItem.getActionView();
        ImageView closeButtonSearch = (ImageView) searchView.findViewById(R.id.search_close_btn);

        if(searchQuery != null) {
            searchView.setQuery(searchQuery, false);
        }

        //Quando scrivo nella searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query;

                //Log.d(TAG, "onQueryTextSubmit: ");
                final Observer<List<Meal>> observer = new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(List<Meal> meals) {
                        //Log.d(TAG, "onChanged: ");
                        for(int i=0; i<meals.size(); i++){
                            Log.d(TAG, "Ricetta " + i + " " + meals.get(i).getStrMeal());
                        }
                    }
                };


                mealViewModel.getMeals(searchQuery).observe(SearchActivity.this, observer);



                Toast.makeText(SearchActivity.this, searchQuery, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        //gestisco click sul bottone clear della searchView per rimuovere lo stato savato
        //che altrimenti ruotando lo schermo verrebbe scorrettamente visualizzato.
        closeButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                searchQuery = null;
            }
        });


        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Intent goBackMain = new Intent(SearchActivity.this, MainActivity.class);
                goBackMain.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(goBackMain);
                return false;
            }
        });
        return true;
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SEARCH_QUERY, searchQuery);
        super.onSaveInstanceState(outState);
    }


}
