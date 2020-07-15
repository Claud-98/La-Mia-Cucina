package com.example.lamiacucina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lamiacucina.R;
import com.example.lamiacucina.models.Meal;

import java.util.List;

public class FavouriteMealAdapter extends RecyclerView.Adapter<FavouriteMealAdapter.FavouriteMealViewHolder> {

    public interface OnItemClickListener{

        void onItemClick(Meal meal);
    }

    private List<Meal> mealList;
    private LayoutInflater layoutInflater;
    private FavouriteMealAdapter.OnItemClickListener onItemClickListener;

    public static class FavouriteMealViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMealName;

        public FavouriteMealViewHolder (View v) {
            super(v);
            textViewMealName = v.findViewById(R.id.textViewMealName);
        }

        public void bind(Meal meal, FavouriteMealAdapter.OnItemClickListener onItemClickListener){
            textViewMealName.setText(meal.getStrMeal());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(meal);
                }
            });
        }

    }


    public FavouriteMealAdapter(Context context, List<Meal> mealList, FavouriteMealAdapter.OnItemClickListener onItemClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mealList = mealList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FavouriteMealAdapter.FavouriteMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  this.layoutInflater.inflate(R.layout.meal_item_favourite, parent, false);
        return new FavouriteMealAdapter.FavouriteMealViewHolder(view);
    }


    public void onBindViewHolder(@NonNull FavouriteMealAdapter.FavouriteMealViewHolder holder, int position) {

        holder.bind(mealList.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (mealList != null) {
            return mealList.size();
        }else{
            return 0;
        }
    }

}
