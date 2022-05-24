package com.example.carbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

public class LoginActivity extends AppCompatActivity {
    String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
Button btn;
EditText mobile;
    private static final String TAG = "LoginActivity";
    String phoneNumber, otp;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile = findViewById(R.id.mob);
        btn = findViewById(R.id.log);
        mAuth = FirebaseAuth.getInstance();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobile.equals("") || mobile.equals(null)||mobile.length()<10) {
                    mobile.setError("Enter a right mobile number");
                    return;
                }
                String phoneNo = mobile.getText().toString();
                Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);
                intent.putExtra("phoneNo",phoneNo);
                startActivity(intent);
            }

        });
    }
}