package com.test.pandemic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Vendor extends AppCompatActivity {
    EditText emailID,password;
    Button btnLogin;
    Button btnUser;
    Button btnAdmin;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailID=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        btnLogin=findViewById(R.id.button);
        btnUser=findViewById(R.id.button2);
        btnAdmin=findViewById(R.id.button3);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser!=null){
                    Toast.makeText(Vendor.this,"Hasta lavista Baby",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Vendor.this,NewCustDas.class);
                    startActivity(i);
                    finish();
                }
//                else{
//                    Toast.makeText(Vendor.this,"Please Try again!",Toast.LENGTH_SHORT).show();
//
//                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailID.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty()){
                    emailID.setError("Please enter the email id");
                    emailID.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter the password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(Vendor.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(Vendor.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(Vendor.this,"Login Error,please Login Again",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(Vendor.this, "Error Occred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intUser=new Intent(Vendor.this,MainActivity.class);
                startActivity(intUser);
                finish();
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intAdmin=new Intent(Vendor.this,Admin.class);
                startActivity(intAdmin);
                finish();
            }
        });

    }
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }


}
