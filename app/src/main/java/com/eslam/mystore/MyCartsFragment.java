package com.eslam.mystore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eslam.mystore.activities.PlaceOrder_activity;
import com.eslam.mystore.adapters.MyCartadabter;
import com.eslam.mystore.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartsFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

Button  buynow;
    TextView overTotelmount;
    public BroadcastReceiver mmessgerecever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totlbill = intent.getIntExtra("totlmount", 0);
            overTotelmount.setText("Total Price : " + totlbill + "$");
        }
    };
    RecyclerView recyclerView;
    MyCartadabter myCartadabter;
    List<MyCartModel> myCartModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = root.findViewById(R.id.recy_addtocart);
        buynow=root.findViewById(R.id.buy_now);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        overTotelmount = root.findViewById(R.id.textView3);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mmessgerecever
                , new IntentFilter("Mytotlmount")
        );

        myCartModelList = new ArrayList<>();
        myCartadabter = new MyCartadabter(getActivity(), myCartModelList);
        recyclerView.setAdapter(myCartadabter);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                        myCartModelList.add(myCartModel);
                        myCartadabter.notifyDataSetChanged();
                    }
                }
            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), PlaceOrder_activity.class);
                intent.putExtra("itemList", (Serializable) myCartModelList);
                startActivity(intent);
            }
        });

        return root;
    }
}