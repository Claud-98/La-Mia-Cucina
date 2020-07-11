package com.example.lamiacucina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        ImageView mealImage;


        public SearchMealViewHolder (View v) {
            super(v);
            textViewMealName = v.findViewById(R.id.textViewMealName);
            mealImage= v.findViewById(R.id.mealImage);

        }

        public void bind(Meal meal, OnItemClickListener onItemClickListener){
            Glide.with(itemView.getContext()).load(meal.getStrMealThumb()).into(mealImage);
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

    public void setData(List<Meal> mealList){
        if (mealList != null){
            this.mealList = mealList;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SearchMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =  this.layoutInflater.inflate(R.layout.meal_item, parent, false);
       return new SearchMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMealViewHolder holder, int position) {

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

    public void clearList(){
        if(mealList!= null)
            mealList.clear();
    }
}
