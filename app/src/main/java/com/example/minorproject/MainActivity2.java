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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private TextView title3;
    private EditText name, age, email2, policeid, password2;
    private ProgressBar progressBar2;
    private Button register2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        title3 = (TextView) findViewById(R.id.title3);
        title3.setOnClickListener(this);

        register2 = (Button) findViewById(R.id.register2);
        register2.setOnClickListener(this);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        email2 = (EditText) findViewById(R.id.email2);
        policeid = (EditText) findViewById(R.id.policeid);
        password2 = (EditText) findViewById(R.id.password2);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title3:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register2:
                register2();
                break;

        }

    }

    private void register2() {
        String email3 = email2.getText().toString().trim();
        String password3 = password2.getText().toString().trim();
        String name2 = name.getText().toString().trim();
        String age2 = age.getText().toString().trim();
        String policeid2 = policeid.getText().toString().trim();

        if (name2.isEmpty()) {
            name.setError("Full Name is Required!!");
            name.requestFocus();
            return;
        }
        if (age2.isEmpty()) {
            age.setError("Enter your Age!!");
            age.requestFocus();
            return;
        }
        if (policeid2.isEmpty()) {
            policeid.setError("Enter your Police Id!!");
            policeid.requestFocus();
            return;
        }
        if (email3.isEmpty()) {
            email2.setError("Email is Required!!");
            email2.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email3).matches()) {
            email2.setError("Please provide valid email!!");
            email2.requestFocus();
            return;
        }
        if (password3.isEmpty()) {
            password2.setError("Create Password!!");
            password2.requestFocus();
            return;
        }
        if (password3.length() < 6) {
            password2.setError("Password should have atleast 6 characters!!");
            password2.requestFocus();
            return;
        }

        progressBar2.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email3 , password3)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(name2,age2,email3);

                            FirebaseDatabase.getInstance().getReference("user")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task){

                                    if(task.isSuccessful()){
                                        Toast.makeText(MainActivity2.this , "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar2.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(MainActivity2.this , "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar2.setVisibility(View.GONE);
                                    }

                                }
                            });


                        }else{
                            Toast.makeText(MainActivity2.this , "Failed to register! Try Again!!", Toast.LENGTH_LONG).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });

    }
}