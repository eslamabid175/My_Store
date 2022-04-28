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
import com.eslam.mystore.activities.Viewall;
import com.eslam.mystore.models.PopularModel;

import java.util.List;

public class PopularAdabters extends RecyclerView.Adapter<PopularAdabters.ViewHolder> {
    private Context context;
    private List<PopularModel> popularModelList;


    public PopularAdabters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularModelList.get(position).getImge_url()).into(holder.pop_img);
        holder.name.setText(popularModelList.get(position).getName());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.discount.setText(popularModelList.get(position).getDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Viewall.class);
                intent.putExtra("type", popularModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
        // Intent i=new Intent(context,Viewall.class);
        //i.putExtra("type",popularModelList.get(position).getType());
        //context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pop_img;
        TextView name, description, discount, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pop_img = itemView.findViewById(R.id.pop_image);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_desc);
            discount = itemView.findViewById(R.id.pop_discount);
            rating = itemView.findViewById(R.id.pop_rating);
        }
    }
}
