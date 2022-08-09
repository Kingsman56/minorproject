package com.example.minorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity4 extends AppCompatActivity {

    private EditText email5;
    private Button reset;
    private ProgressBar progressBar3;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        email5 = (EditText) findViewById(R.id.email5);
        reset = (Button) findViewById(R.id.reset);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void reset() {
        String email6 = email5.getText().toString().trim();

        if(email6.isEmpty()){
            email5.setError("Email is valid!!");
            email5.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email6).matches()){
            email5.setError("Please provide a valid email address");
            email5.requestFocus();
            return;
        }

        progressBar3.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email6).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity4.this , "Check your email to reset your Password!!", Toast.LENGTH_LONG).show();
                    progressBar3.setVisibility(View.GONE);
                }else{
                    Toast.makeText(MainActivity4.this , "Try again! Something went wrong!",Toast.LENGTH_LONG).show();
                    progressBar3.setVisibility(View.GONE);
                }

            }
        });

    }
}