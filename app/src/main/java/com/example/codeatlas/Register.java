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

public class Register extends AppCompatActivity {
    // variables
    private EditText regEmail, regPassword, confirmPassword;
    private Button RegisterButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Firebase instance
        mAuth = FirebaseAuth.getInstance();
        // typecasting variables
        regEmail = findViewById(R.id.edEmail);
        regPassword = findViewById(R.id.edPassword);
        confirmPassword = findViewById(R.id.edConfirmPassword);
        RegisterButton = findViewById(R.id.btRegister);

    }
    // register method
    public void registerOnClick(View view){
        // if user clicks on the button --> get values --> into the variables
        if (view.getId()== R.id.btRegister){
            String email = regEmail.getText().toString().trim();
            String password = regPassword.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();


            if (TextUtils.isEmpty(email)){
                Toast.makeText(this, "Enter an email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(confirmPass)){
                Toast.makeText(this, "Enter a confirm password", Toast.LENGTH_SHORT).show();
                return;
            }
            // range checks -- prevent passwords shorter than 6
            if(password.length()<6 || confirmPass.length()<6){
                Toast.makeText(this,"Password must be greater than 6 characters",Toast.LENGTH_SHORT).show();
                return;
            }
            if (confirmPass.equals(password)){
                // bring in fire
                // create thus user
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Reg Successful", Toast.LENGTH_SHORT).show();
                            // allow to login
                            Intent intent = new Intent(Register.this,Login.class);
                            startActivity(intent);
                            finish();
                        } else{
                            Toast.makeText(Register.this, "Reg Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }
}