package com.eslam.mystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eslam.mystore.R;
import com.eslam.mystore.models.MyCartModel;

import java.util.List;

public class MyCartadabter extends RecyclerView.Adapter<MyCartadabter.ViewHolder> {
    Context context;
    List<MyCartModel> list;
    int totlprice = 0;

    public MyCartadabter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycartitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nme.setText(list.get(position).getProductName());
        holder.price.setText(String.valueOf(list.get(position).getProductPrice()) + "$");
        holder.dte.setText(list.get(position).getCurrentDate());
        holder.totlquntity.setText(list.get(position).getTotlalQuantity());
        holder.totlprice.setText(String.valueOf(list.get(position).getTotalPrice()) + "$");
        totlprice = totlprice + list.get(position).getTotalPrice();
        Intent intent = new Intent("Mytotlmount");
        intent.putExtra("totlmount", totlprice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nme, price, dte, time, totlquntity, totlprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nme = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            dte = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            totlquntity = itemView.findViewById(R.id.total_quntity);
            totlprice = itemView.findViewById(R.id.total_price);
        }
    }
}
