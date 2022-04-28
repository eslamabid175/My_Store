package com.eslam.mystore.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eslam.mystore.R;
import com.eslam.mystore.adapters.ViewallAdabter;
import com.eslam.mystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Viewall extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    ViewallAdabter viewallAdabter;
    List<ViewAllModel> viewAllModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);


        firebaseFirestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.viewall_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewallAdabter = new ViewallAdabter(this, viewAllModelList);
        recyclerView.setAdapter(viewallAdabter);
//fruits
        if (type != null && type.equalsIgnoreCase("fruit")) {

            firebaseFirestore.collection("AllProducts").
                    whereEqualTo("type", "fruit")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewallAdabter.notifyDataSetChanged();
                    }
                }
            });

        }

//vege
        if (type != null && type.equalsIgnoreCase("Vegatbels")) {
            firebaseFirestore.collection("AllProducts").whereEqualTo("type", "Vegatbels").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewallAdabter.notifyDataSetChanged();
                    }
                }
            });
        }
        //fish
        if (type != null && type.equalsIgnoreCase("fish")) {

            firebaseFirestore.collection("AllProducts").
                    whereEqualTo("type", "fish")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewallAdabter.notifyDataSetChanged();
                    }
                }
            });

        }

//eggs
        if (type != null && type.equalsIgnoreCase("eggs")) {
            firebaseFirestore.collection("AllProducts").whereEqualTo("type", "eggs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewallAdabter.notifyDataSetChanged();
                    }
                }
            });
        }
        //milk
        if (type != null && type.equalsIgnoreCase("milk")) {
            firebaseFirestore.collection("AllProducts").whereEqualTo("type", "milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewallAdabter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}