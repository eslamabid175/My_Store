package com.eslam.mystore.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eslam.mystore.R;
import com.eslam.mystore.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    EditText fullname, enteremail, enterpassword, enterpassword2, phone;
    // هنكريت اوبجيكت من databaseRefrence class عشان نأكسز firebase real time database
    //   DatabaseReference databaseReference =
    //         FirebaseDatabase.getInstance().getReferenceFromUrl("https://my-store-71a97-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        fullname = findViewById(R.id.name);
        enteremail = findViewById(R.id.email_register);
        enterpassword = findViewById(R.id.password_register);
        enterpassword2 = findViewById(R.id.password_register2);
        phone = findViewById(R.id.phone);

        final TextView loginNow = findViewById(R.id.loginNow_tv);
        final Button register_btn = findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createuser();
            }
        });


        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   startActivity(new Intent(Register.this,Login.class));
                finish();
            }
        });
    }

    private void createuser() {
        String fullnametxt = fullname.getText().toString();
        String emtxt = enteremail.getText().toString();
        String phonetxt = phone.getText().toString();
        String pass1txt = enterpassword.getText().toString();
        String pass2txt = enterpassword2.getText().toString();
// اتاكد انه مالي كل الداتا
        if (fullnametxt.isEmpty() || emtxt.isEmpty() || pass1txt.isEmpty() || pass2txt.isEmpty()) {
            Toast.makeText(Register.this, "please fill all fields", Toast.LENGTH_SHORT).show();

        }
        //شوف لو الباسوردات زي بعض ولا لا
        else if (!pass1txt.equals(pass2txt)) {
            Toast.makeText(Register.this, "passwords are not matching", Toast.LENGTH_SHORT).show();
        } else {

            auth.createUserWithEmailAndPassword(emtxt, pass1txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        UserModel userModel = new UserModel(fullnametxt, emtxt, phonetxt, pass1txt);
                        String id = task.getResult().getUser().getUid();
                        db.getReference().child("Users").child(id).setValue(userModel);
                        Toast.makeText(Register.this, "User Rigester Succfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Register.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
//            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.hasChild(phonetxt)) {
//
//                        Toast.makeText(Register.this, "this phone is already registered", Toast.LENGTH_SHORT).show();
//                    } else {
//
//
//                        databaseReference.child("users").child(phonetxt).child("fullname").setValue(fullnametxt);
//                        databaseReference.child("users").child(phonetxt).child("emial").setValue(emtxt);
//                        databaseReference.child("users").child(phonetxt).child("password").setValue(pass1txt);
//
//                        Toast.makeText(Register.this, "User Rigester Succfully", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

        }
    }


}