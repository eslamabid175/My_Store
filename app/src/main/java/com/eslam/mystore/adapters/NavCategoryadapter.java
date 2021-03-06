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
import com.eslam.mystore.activities.Nav_catecoryActivity;
import com.eslam.mystore.models.NavCategoryModel;

import java.util.List;

public class NavCategoryadapter extends RecyclerView.Adapter<NavCategoryadapter.ViewHolder> {
    Context context;
    List<NavCategoryModel> list;

    public NavCategoryadapter(Context context, List<NavCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.discribtion.setText(list.get(position).getDescription());
        holder.descount.setText(list.get(position).getDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Nav_catecoryActivity.class);
                intent.putExtra("type",list.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, discribtion, descount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_image);
            name = itemView.findViewById(R.id.nav_cat_namee);
            descount   = itemView.findViewById(R.id.nav_cat_disc);
            discribtion  = itemView.findViewById(R.id.nav_cat_desc);
        }
    }
}
