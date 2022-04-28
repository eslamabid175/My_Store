package com.eslam.mystore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eslam.mystore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emial, password;
    // هنكريت اوبجيكت من databaseRefrence class عشان نأكسز firebase real time database

    //DatabaseReference databaseReference =
    //      FirebaseDatabase.getInstance().getReferenceFromUrl("https://my-store-71a97-default-rtdb.firebaseio.com/");
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity.class));

            Toast.makeText(this, "alredy signed in", Toast.LENGTH_SHORT).show();
            finish();
        }

        emial = findViewById(R.id.email_login);
        //   final EditText phone = findViewById(R.id.phone_login);
        password = findViewById(R.id.password_login);
        final Button login_bt = findViewById(R.id.login_btn);
        final TextView register_now = findViewById(R.id.registerNow_tv);


        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();

            }
        });

        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));

            }
        });


    }

    private void loginuser() {

        final String emialtxt = emial.getText().toString();
        //final String emiltxt = phone.getText().toString();

        final String passwordtxt = password.getText().toString();

        if (emialtxt.isEmpty() || passwordtxt.isEmpty()) {
            Toast.makeText(Login.this, "please Enter your Email or password", Toast.LENGTH_SHORT).show();


        } else {

//                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.hasChild(phonetxt)) {
//
//                                final String getpass = snapshot.child(phonetxt).child("password").getValue(String.class);
//                                if (getpass.equals(passwordtxt)) {
//                                    Toast.makeText(Login.this, "Succufully loggin", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(Login.this, MainActivity.class));
//                                    finish();
//                                } else {
//                                    Toast.makeText(Login.this, "wrong password or phone number ", Toast.LENGTH_SHORT).show();
//
//                                }
//
//                            } else {
//
//                                Toast.makeText(Login.this, "wrong password or phone number", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });

            auth.signInWithEmailAndPassword(emialtxt, passwordtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login succ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "erorr" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}