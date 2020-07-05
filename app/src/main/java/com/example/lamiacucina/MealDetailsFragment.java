package com.example.lamiacucina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MealDetailsFragment extends Fragment {

    private FragmentMealDetailsBinding binding;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private IngredientsFragment ingredientsFragment;
    private CookingProcessFragment cookingProcessFragment;
    private String COOKING_PROCESS = "Cooking Process";
    private String INGREDIENTS = "Ingredients";
    private  Meal meal;



    public MealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMeal();



        if(meal.getStrMeal() == null){
            Log.d("MealDetailsFragment", "onViewCreated: Sono Nullo");
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(meal.getStrMeal());
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

}
