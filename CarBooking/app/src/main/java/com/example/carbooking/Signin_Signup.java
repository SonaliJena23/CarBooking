package com.example.carbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Signin_Signup extends AppCompatActivity {
private Button btn1,btn2;
private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);
        btn1 = findViewById(R.id.login);
        btn2 = findViewById(R.id.reg);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin_Signup.this, LoginActivity.class);
                startActivity(intent);
            }
        });
btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Signin_Signup.this, RegistrationActivity.class);
        startActivity(intent);
    }
});
    }
}