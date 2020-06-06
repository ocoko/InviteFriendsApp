package com.example.invitefriends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText emailId, password;
    Button btnLogin;
    TextView tvLogin;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvLogin = findViewById(R.id.textid);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if(mFirebaseUser != null) {
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Login.this, "Please LogIn.", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter your email!");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password!");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(Login.this, "Fields are empty!", Toast.LENGTH_SHORT).show();

                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this, "Login Error, Please Try Again.", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intToMain = new Intent(Login.this, MainActivity.class);
                                startActivity(intToMain);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRegister = new Intent(Login.this, Register.class);
                startActivity(intRegister);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
