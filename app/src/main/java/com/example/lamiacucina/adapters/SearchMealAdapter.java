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

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.SearchMealViewHolder> {

    public interface OnItemClickListener{

        void onItemClick(Meal meal);

    }

    private List<Meal> mealList;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public static class SearchMealViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMealName;


        public SearchMealViewHolder (View v) {
            super(v);
            textViewMealName = v.findViewById(R.id.textViewMealName);

        }

        public void bind(Meal meal, OnItemClickListener onItemClickListener){

            textViewMealName.setText(meal.getStrMeal());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(meal);
                }
            });
        }

    }

    public SearchMealAdapter(Context context, List<Meal> mealList, OnItemClickListener onItemClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mealList = mealList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SearchMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =  this.layoutInflater.inflate(R.layout.meal_item, parent, false);
       return new SearchMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMealViewHolder holder, int position) {
        //holder.textViewMealName.setText(mealList.get(position).getStrMeal());
        holder.bind(mealList.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }
}
