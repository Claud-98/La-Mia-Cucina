package com.example.lamiacucina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lamiacucina.adapters.ViewPagerAdapter;
import com.example.lamiacucina.databinding.FragmentMealDetailsBinding;
import com.example.lamiacucina.models.Meal;
import com.example.lamiacucina.viewmodels.MealViewModel;
import com.google.android.material.tabs.TabLayout;
import java.util.List;



public class MealDetailsFragment extends Fragment {

    private FragmentMealDetailsBinding binding;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private IngredientsFragment ingredientsFragment;
    private CookingProcessFragment cookingProcessFragment;
    private String COOKING_PROCESS = "Cooking Process";
    private String INGREDIENTS = "Ingredients";
    private  Meal meal;
    private ActionBar actionBar;
    private MealViewModel mealViewModel;
    private boolean favVisible;
    private Menu menu;
    private List<Meal> mealsList;



    public MealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mealViewModel = new ViewModelProvider(this.getActivity()).get(MealViewModel.class);
        mealsList = mealViewModel.getAllMeals().getValue();

        meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMeal();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMealDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {


        MenuItem item = menu.getItem(0);
        item.setVisible(false);

        inflater.inflate(R.menu.show_meal_fragment_menu, menu);
        this.menu = menu;

        menu.findItem(R.id.Fav_del_MA).setVisible(favVisible);
        menu.findItem(R.id.Favourite_MA).setVisible(!favVisible);


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Favourite_MA:
                favVisible = false;
                item.setVisible(false);
                menu.findItem(R.id.Fav_del_MA).setVisible(true);
                mealViewModel.insertIMeal(meal);
                return true;
            case R.id.Fav_del_MA:
                favVisible = true;
                item.setVisible(false);
                menu.findItem(R.id.Favourite_MA).setVisible(true);
                mealViewModel.deleteMeal(meal);
                return true;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        if(meal.getStrMeal() == null){
            Log.d("MealDetailsFragment", "onViewCreated: Sono Nullo");
        }
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(meal.getStrMeal());
        viewPager = binding.viewPager;
        tabLayout = binding.tabLayout;


        ingredientsFragment = new IngredientsFragment();
        newIngredientsFragment(INGREDIENTS, meal, ingredientsFragment);
        cookingProcessFragment = new CookingProcessFragment();
        newCookingProcessFragment(COOKING_PROCESS, meal.getStrInstructions(), cookingProcessFragment);


        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(ingredientsFragment, "Ingredients");
        viewPagerAdapter.addFragment(cookingProcessFragment, "Cooking Process");
        viewPager.setAdapter(viewPagerAdapter);






        if(mealViewModel.getAllMeals().getValue() != null) {
            Log.d("TAG", "LA Dimensione della lista è:  " + mealViewModel.getAllMeals().getValue().size() + "L'altra lista è: " + mealsList.size());
            if(mealsList!=null){
            for (int i=0; i<mealsList.size();i++)
                Log.d("TAG", "Elemento " + i + " " +mealsList.get(i).getIdMeal());
            }
        }


    }


    private void newIngredientsFragment(String textKey, Meal meal, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(textKey, meal);
        fragment.setArguments(bundle);
    }

    private void newCookingProcessFragment(String textKey, String text, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString(textKey, text);
        fragment.setArguments(bundle);
    }

    private boolean isInTheList(List<Meal> mealDB, Meal MealAPI){

        if(mealDB == null) {
            return true;
        } else {
            for (int i = 0; i < mealDB.size(); i++) {
                if(mealDB.get(i).getIdMeal() == (float) MealAPI.getIdMeal()){
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        favVisible = isInTheList(mealsList, meal);
        Log.d("onStart: ","La variabile è " + favVisible);
    }
}
