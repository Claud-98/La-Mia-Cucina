package com.example.lamiacucina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lamiacucina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String searchQuery;
    private String SEARCH_QUERY = "searchQuery";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        androidx.appcompat.widget.Toolbar toolbar;
        toolbar = binding.mainActivityToolbar;
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_menu));
        toolbar.setTitle("@string/app_title");

        if(savedInstanceState != null){
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        final MenuItem searchItem = menu.findItem(R.id.Search_MA);
        final SearchView searchView = (SearchView) searchItem.getActionView();


        //TODO - creare un metodo setVisibility che racchiude qyeste operazioni

        if(searchQuery != null) {
            searchItem.expandActionView();
            searchView.setQuery(searchQuery, true);
            menu.findItem(R.id.Add_MA).setVisible(false);
            menu.findItem(R.id.Favourite_MA).setVisible(false);


        }


        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                menu.findItem(R.id.Add_MA).setVisible(false);
                menu.findItem(R.id.Favourite_MA).setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                menu.findItem(R.id.Add_MA).setVisible(true);
                menu.findItem(R.id.Favourite_MA).setVisible(true);

                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query;
                Toast.makeText(MainActivity.this, searchQuery, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
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
