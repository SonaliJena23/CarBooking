package com.example.carbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOtp extends AppCompatActivity {
    private EditText etOTP;
    private TextView btnVerify,tv_timer,btnresendtext,btn_resend;
    private String OTP;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Context context;
    private String val_phone;
    private static final String TAG = "VerifyOtp";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String verificationCodeBySystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        etOTP = (EditText) findViewById(R.id.et_otp);
        btnVerify = findViewById(R.id.btn_VerifyPhoneNo);
        btn_resend =findViewById(R.id.btn_resend);
        btnresendtext=findViewById(R.id.btn_resend_text);
        tv_timer=findViewById(R.id.tv_timer);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = etOTP.getText().toString();

                if(code.isEmpty() || code.length() < 6){
                    etOTP.setError("Wrong OTP...");
                    etOTP.requestFocus();
                    return;
                }

                startActivity(new Intent(VerifyOtp.this, DashBoard.class));
                finish();
            }
        });
    }








}