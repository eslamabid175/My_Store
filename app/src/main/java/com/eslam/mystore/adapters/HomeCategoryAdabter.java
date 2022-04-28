package com.eslam.mystore.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eslam.mystore.R;
import com.eslam.mystore.activities.Viewall;
import com.eslam.mystore.models.HomeCategory;

import java.util.List;

public class HomeCategoryAdabter extends RecyclerView.Adapter<HomeCategoryAdabter.ViewHolder> {

    Context context;
    List<HomeCategory> categoryList;

    public HomeCategoryAdabter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(categoryList.get(position).getImge_url()).into(holder.categoryImage);
        holder.name.setText(categoryList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Viewall.class);
                intent.putExtra("type", categoryList.get(position).getType());
                context.startActivity(intent);
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, Viewall.class);
//                intent.putExtra("type",categoryList.get(position).getType());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView name;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryImage = itemView.findViewById(R.id.home_cat_image);
            name = itemView.findViewById(R.id.home_cat_name);

        }
    }
}
