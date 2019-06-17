package com.dev.dannpa.androideat.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.dannpa.androideat.Interface.ItemClickListener;
import com.dev.dannpa.androideat.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtFoodName;
    public ImageView txtFoodImage;

    private ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);

        txtFoodName = itemView.findViewById(R.id.food_name);
        txtFoodImage = itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
