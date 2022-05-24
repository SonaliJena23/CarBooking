package com.example.carbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

private TextView have_an_account;
Button button;
    FirebaseAuth auth;  //FirebaseAuth Instance
    private TextInputEditText regName,inputEmail,mobileNo,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
         regName = (TextInputEditText)findViewById(R.id.name);
         inputEmail = (TextInputEditText)findViewById(R.id.email);
         mobileNo = (TextInputEditText)findViewById(R.id.mobile);
         pwd = (TextInputEditText)findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();    //Getting instance of FirebaseAuth

        have_an_account = findViewById(R.id.already_have_an_account);
      button = findViewById(R.id.login_ButtonID);
        button.setOnClickListener(this);

        have_an_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void submitForm() {
        String emailInput = inputEmail.getText().toString().trim();
        String password = pwd.getText().toString().trim();
        final String user = regName.getText().toString().trim();
        //first validate the form then move ahead
        if (!(Pattern.matches("^[\\p{L} .'-]+$", regName.getText()))) {
            regName.setError("Please Enter Valid Name");
            regName.setFocusable(true);
        }
        else if(!(Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", inputEmail.getText()))) {
            inputEmail.setError("Please Enter Valid EmailAddress");
            inputEmail.setFocusable(true);
        }
      else  if (mobileNo.equals("") || mobileNo.equals(null)||mobileNo.length()<10) {
            mobileNo.setError("Enter a right mobile number");
            return;
        }
      else if (!(Pattern.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", pwd.getText()))) {
            pwd.setError("Please Enter Valid Password");
            pwd.setFocusable(true);
        }   //create user with email/password by adding complete listener
        auth.createUserWithEmailAndPassword(emailInput, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegistrationActivity.this, "Registration successfull" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        // If sign-in fails, display a message to the user. If sign-in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_LONG).show();
                            Log.e("failed", task.getException().toString());
                        } else {

                            startActivity(new Intent(RegistrationActivity.this, DashBoard.class));
                            finish();
                        }
                    }
                });
    }

    public void onClick(View view) {

            submitForm();
        if (view == button) {

        }

    }
}

