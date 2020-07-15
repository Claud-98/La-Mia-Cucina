package com.example.lamiacucina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lamiacucina.adapters.FavouriteMealAdapter;
import com.example.lamiacucina.databinding.FragmentFavouriteBinding;
import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.viewmodels.MealViewModel;


public class FavouriteFragment extends Fragment {
    private FragmentFavouriteBinding binding;
    private MealViewModel mealViewModel;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mealViewModel = new ViewModelProvider(this.getActivity()).get(MealViewModel.class);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavouriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Search:
                Navigation.findNavController(getView()).navigate(R.id.startSearchAction);
                return true;
                default:
                break;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("La Mia Cucina");





        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.showMealRecyclerView.setLayoutManager(layoutManager);

        FavouriteMealAdapter favouriteMealAdapter = new FavouriteMealAdapter(getActivity(), mealViewModel.getAllMeals().getValue(), new FavouriteMealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal) {
                FavouriteFragmentDirections.ShowFavourite action = FavouriteFragmentDirections.showFavourite(meal);
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        binding.showMealRecyclerView.setAdapter(favouriteMealAdapter);

    }
}
