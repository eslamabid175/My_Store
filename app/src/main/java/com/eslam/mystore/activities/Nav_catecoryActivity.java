package com.eslam.mystore.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eslam.mystore.R;
import com.eslam.mystore.adapters.NavCategoryDetaildAdapter;
import com.eslam.mystore.models.NavCategoryDetaildModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Nav_catecoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_catecory);
        RecyclerView recyclerView;
        List<NavCategoryDetaildModel> list;
        NavCategoryDetaildAdapter adapter;
FirebaseFirestore db;


db=FirebaseFirestore.getInstance();
String type=getIntent().getStringExtra("type");
recyclerView=findViewById(R.id.nav_cat_det_recy);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter=new NavCategoryDetaildAdapter(this,list);
recyclerView.setAdapter(adapter);

        if (type != null && type.equalsIgnoreCase("drink")) {
            db.collection("NavCategoryDetaild").
                    whereEqualTo("type", "drink").
                    get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot   : task.getResult().getDocuments()) {
                        NavCategoryDetaildModel navCategoryDetaildModel =
                                documentSnapshot.toObject(NavCategoryDetaildModel.class);
                                list.add(navCategoryDetaildModel);
                                adapter.notifyDataSetChanged();
                    }
                }
            });
        }
//        db.collection("NavCategoryDetaild")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                NavCategoryDetaildModel navCategoryDetaildModel = document.toObject(NavCategoryDetaildModel.class);
//                                list.add(navCategoryDetaildModel);
//                                adapter.notifyDataSetChanged();
//                            }
//                        } else {
//                            Toast.makeText(Nav_catecoryActivity.this, "Erorr" + task.getException(), Toast.LENGTH_SHORT).show();                        }
//                    }
//                });

    }
}