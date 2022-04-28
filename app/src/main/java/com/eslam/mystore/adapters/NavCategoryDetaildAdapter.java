package com.eslam.mystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eslam.mystore.R;
import com.eslam.mystore.models.NavCategoryDetaildModel;

import java.util.List;

public class NavCategoryDetaildAdapter extends RecyclerView.Adapter<NavCategoryDetaildAdapter.ViewHolder> {
    Context context;
    List<NavCategoryDetaildModel> list;

    public NavCategoryDetaildAdapter(Context context, List<NavCategoryDetaildModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.nav_cat_det_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cat_nav_det_image);
        name=itemView.findViewById(R.id.nav_catt_name);
        price=itemView.findViewById(R.id.nav_cat_price);
        }
    }
}
