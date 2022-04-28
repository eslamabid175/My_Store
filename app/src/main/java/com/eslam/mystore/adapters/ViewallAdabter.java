package com.eslam.mystore.adapters;

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
import com.eslam.mystore.activities.DetaledActivity;
import com.eslam.mystore.models.ViewAllModel;

import java.util.List;

public class ViewallAdabter extends RecyclerView.Adapter<ViewallAdabter.ViewHolder> {
    Context context;
    List<ViewAllModel> list;

    public ViewallAdabter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImge_url()).into(holder.iv);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice() + "/Kg");
        if (list.get(position).getType().equals("eggs")) {
            holder.price.setText(list.get(position).getPrice() + "/dozen");
        }
        if (list.get(position).getType().equals("milk")) {
            holder.price.setText(list.get(position).getPrice() + "/litre");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetaledActivity.class);
                intent.putExtra("detail", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView name, description, price, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.view_image);
            name = itemView.findViewById(R.id.view_name);
            description = itemView.findViewById(R.id.view_desc);
            rating = itemView.findViewById(R.id.view_rating);
            price = itemView.findViewById(R.id.view_price);
        }
    }
}
