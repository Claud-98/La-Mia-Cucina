package com.example.lamiacucina;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lamiacucina.adapters.SearchMealAdapter;
import com.example.lamiacucina.databinding.FragmentShowMealBinding;
import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.viewmodels.MealViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMealFragment extends Fragment {

    private static final String TAG = "ShowMealFragment";
    private MealViewModel mealViewModel;
    private FragmentShowMealBinding binding;
    private String searchQuery;
    private String SEARCH_QUERY = "searchQuery";
    private  MenuItem searchItem;
    private SearchView searchView;



    public ShowMealFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if(savedInstanceState != null){
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        }



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SEARCH_QUERY, searchQuery);
        super.onSaveInstanceState(outState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        }

        binding = FragmentShowMealBinding.inflate(getLayoutInflater());
        return binding.getRoot();



    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.getItem(0);

        searchItem.expandActionView();

        final SearchView searchView = (SearchView) searchItem.getActionView();
        ImageView closeButtonSearch = (ImageView) searchView.findViewById(R.id.search_close_btn);


        if(searchQuery != null) {

            searchView.setQuery(searchQuery, true);
            searchView.clearFocus();
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery=query;

                final Observer<List<Meal>> observer = new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(List<Meal> meals) {
                        Log.d(TAG, "onChanged: ");




                        for(int i=0; i<meals.size(); i++){

                            Log.d(TAG, "Ricetta " + i + " " + meals.get(i).getStrMeal());

                        }
                        Log.d(TAG, "SizeList: " + meals.size());
                        SearchMealAdapter searchMealAdapter = new SearchMealAdapter(getActivity(), meals, new SearchMealAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Meal meal) {

                                ShowMealFragmentDirections.ShowMealDetailsAction action = ShowMealFragmentDirections.showMealDetailsAction(meal);
                                Navigation.findNavController(getView()).navigate(action);
                            }
                        });

                        binding.showMealRecyclerView.setAdapter(searchMealAdapter);
                    }
                };


                mealViewModel.getMeals(searchQuery).observe(getActivity(), observer);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        //gestisco click sul bottone clear della searchView per rimuovere lo stato savato
        //che altrimenti ruotando lo schermo verrebbe scorrettamente visualizzato.
        closeButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", true);
                searchQuery = null;
            }
        });

        //Gestisco il bottone back - Momentanamente con Intent

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                Intent goBackMain = new Intent(getActivity(), MainActivity.class);
                goBackMain.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(goBackMain);
                return false;
            }
        });




    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mealViewModel =new ViewModelProvider(requireActivity()).get(MealViewModel.class);



        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.showMealRecyclerView.setLayoutManager(layoutManager);





    }






}
