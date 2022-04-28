package com.eslam.mystore.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eslam.mystore.R;
import com.eslam.mystore.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class PlaceOrder_activity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        List<MyCartModel> list= (List<MyCartModel>) getIntent().getSerializableExtra("itemList");

    if(list!=null&&list.size()>0){
for(MyCartModel model :list){

    final HashMap<String, Object> cartMap = new HashMap<>();
    cartMap.put("ProductName", model.getProductName());
    cartMap.put("ProductPrice", model.getProductPrice());
    cartMap.put("currentDate", model.getCurrentDate());
    cartMap.put("currentTime", model.getCurrentTime());
    cartMap.put("totlalQuantity", model.getTotlalQuantity());
    cartMap.put("totalPrice", model.getTotalPrice());
    db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
            .collection("MyOrder").add(cartMap).
            addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(PlaceOrder_activity.this, "Your Order Has Been Blaced", Toast.LENGTH_SHORT).show();

                }
            });

}

    }

    }
}