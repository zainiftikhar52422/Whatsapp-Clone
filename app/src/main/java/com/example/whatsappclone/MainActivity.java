package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

 public class MainActivity extends AppCompatActivity {
    private EditText txtPhoneNumber, txtCode;
    private Button btnVerification;
    private String verificatioId=null;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        txtPhoneNumber=findViewById(R.id.txtPhoneNumber);
        txtCode=findViewById(R.id.txtCode);
        btnVerification=findViewById(R.id.btnVerification);
        userIsLoggedIn();
        btnVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificatioId!=null)
                {
                    verifyPhoneNumberWithCode(verificatioId,txtCode.getText().toString());
                }
                else
                {

                    startPhoneNumberVerification();
                }

            }
        });

        mCallBacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuth(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w("onVerificationFailed", e);
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificatioId=s;
                btnVerification.setText("Verify Code");

            }
        };
    }
    private void  verifyPhoneNumberWithCode(String verificationId, String code)
    {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuth(credential);

    }

     private void signInWithPhoneAuth(PhoneAuthCredential phoneAuthCredential) {

         FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful())
                 {
                     userIsLoggedIn();
                 }
             }
         });
     }

     private void userIsLoggedIn() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this, LogedIn.class);
            startActivity(intent);
            //finish();
            //return;
        }

     }

     private void startPhoneNumberVerification() {
         Toast.makeText(getApplicationContext(),"Hello Javatpoint 0",Toast.LENGTH_SHORT).show();
         PhoneAuthProvider.getInstance().verifyPhoneNumber(
                 txtPhoneNumber.getText().toString(),
                 60,
                 TimeUnit.SECONDS,
                 this,
                 mCallBacks
         );
     }
 }