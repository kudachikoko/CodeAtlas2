package com.example.codeatlas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    // variables
    EditText emailLog;
    EditText passwordLog;
    Button LoginButton,SignButton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailLog = findViewById(R.id.logEmail);
        passwordLog = findViewById(R.id.logPassword);
        LoginButton = findViewById(R.id.btLogin);
        SignButton = findViewById(R.id.btReg);
    }
    public void loginButton(View view) {
        // btn click events --> Paras of view class

        //Toast.makeText(this, "You are offline, Try again later", Toast.LENGTH_SHORT).show();
        String email = emailLog.getText().toString().trim();
        String password = passwordLog.getText().toString().trim();

        // housekeeping
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your email to login", Toast.LENGTH_SHORT).show();
            passwordLog.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    emailLog.setText("");
                    passwordLog.setText("");
                    emailLog.requestFocus();

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
    public void signUp(View view)
    {
        Intent SignUp = new Intent(Login.this,Register.class);
        startActivity(SignUp);

    }
}