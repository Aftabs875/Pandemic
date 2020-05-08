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

public class Admin extends AppCompatActivity {
    EditText emailID,password;
    Button btnLogin;
    Button btnVendor;
    Button btnUser;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailID=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        btnLogin=findViewById(R.id.button);
        btnUser=findViewById(R.id.button2);
        btnVendor=findViewById(R.id.button3);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser!=null){
                    Toast.makeText(Admin.this,"Hasta lavista Baby",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Admin.this,NewCustDas.class);
                    startActivity(i);
                    finish();
                }
//                else{
//                    Toast.makeText(Admin.this,"Please Try again!",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Admin.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(Admin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(Admin.this,"Login Error,please Login Again",Toast.LENGTH_SHORT).show();
                            }
//                            else{
//                                Intent intoHome=new Intent(Admin.this,Dashcustomer.class);
//                                startActivity(intoHome);
//                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Admin.this, "Error Occred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVendor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intVendor=new Intent(Admin.this,Vendor.class);
                startActivity(intVendor);
                finish();
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intUser=new Intent(Admin.this,MainActivity.class);
                startActivity(intUser);
                finish();
            }
        });

    }
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }



}
