package com.eslam.mystore.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eslam.mystore.R;
import com.eslam.mystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetaledActivity extends AppCompatActivity {
    int totlquntity = 1;
    int totlprice = 0;
    ImageView detildImg, additem, removeitem;
    TextView quntity, price, rting, description;
    Button addtocrd;

    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    // FirebaseDatabase database = FirebaseDatabase.getInstance();
    //   DatabaseReference myRef = database.getReference("https://my-store-71a97-default-rtdb.firebaseio.com/");

    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaled);
// toolbr

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        final Object obj = getIntent().getSerializableExtra("detail");
        if (obj instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) obj;
        }
        quntity = findViewById(R.id.quntity);

        detildImg = findViewById(R.id.deteldimge);
        additem = findViewById(R.id.add_item);
        removeitem = findViewById(R.id.remove_item);
        price = findViewById(R.id.dete_price);
        rting = findViewById(R.id.det_rting);
        description = findViewById(R.id.deted_desc);


        if (viewAllModel != null) {

            Glide.with(getApplicationContext()).load(viewAllModel.getImge_url()).into(detildImg);
            rting.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("price :$" + viewAllModel.getPrice() + "/Kg");
            totlprice = viewAllModel.getPrice() * totlquntity;
            if (viewAllModel.getType().equals("eggs")) {
                price.setText("price :$" + viewAllModel.getPrice() + "/dozen");
                totlprice = viewAllModel.getPrice() * totlquntity;
            }
            if (viewAllModel.getType().equals("milk")) {
                price.setText("price :$" + viewAllModel.getPrice() + "/litre");
                totlprice = viewAllModel.getPrice() * totlquntity;
            }
        }
        addtocrd = findViewById(R.id.add_to_crd);
        addtocrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtocrd();
            }
        });

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totlquntity < 10) {
                    totlquntity++;
                    quntity.setText(String.valueOf(totlquntity));
                    totlprice = totlquntity * viewAllModel.getPrice();
                }
            }
        });

        removeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totlquntity > 1) {
                    totlquntity--;
                    quntity.setText(String.valueOf(totlquntity));
                    totlprice = totlquntity * viewAllModel.getPrice();
                }
            }
        });
    }

    private void addtocrd() {
        String savecurrentDate, savecurrentTime;
        Calendar calforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        savecurrentDate = currentDate.format(calforDate.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrentTime = currentTime.format(calforDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("ProductName", viewAllModel.getName());
        cartMap.put("ProductPrice", viewAllModel.getPrice());
        cartMap.put("currentDate", savecurrentDate);
        cartMap.put("currentTime", savecurrentTime);
        cartMap.put("totlalQuantity", quntity.getText().toString());
        cartMap.put("totalPrice", totlprice);
        firestore.collection("CurrentUser").document(firebaseAuth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).
                addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetaledActivity.this, "added succefully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }
}