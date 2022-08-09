package com.example.minorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText email , password;
    private Button login , forgot;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgot = (Button) findViewById(R.id.forgot);
        forgot.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this , MainActivity2.class));
                break;

            case R.id.login:
                userLogin();
                break;

            case R.id.forgot:
                startActivity(new Intent(this, MainActivity4.class));
                break;
        }
    }

    private void userLogin() {

        String email4 = email.getText().toString().trim();
        String password4 =password.getText().toString().trim();

        if(email4.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email4).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }

        if(password4.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if(password4.length()<6){
            password.setError("Password should be atleast 6 characters!");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email4 , password4).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, MainActivity3.class));
                        progressBar.setVisibility(View.GONE);
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this , "Check your email to verify your Account!",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }

                }else{
                    Toast.makeText(MainActivity.this , "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}