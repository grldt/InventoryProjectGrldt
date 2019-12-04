package com.example.dbprojectgrldt;

import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;
    private EditText registerEmail;
    private EditText registerPass;
    private TextView loginTxt;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.regisBtn);

        loginTxt = findViewById(R.id.gotoLogin);

        registerEmail = findViewById(R.id.emailRegis);
        registerPass = findViewById(R.id.passRegis);

        registerButton.setOnClickListener(this);
        loginTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == loginTxt){
            finish();
        }
    }

    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String pass = registerPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Email cannot be empty!", Toast.LENGTH_SHORT).show();
            registerButton.setVisibility(View.VISIBLE);
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            //pass is empty
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
            registerButton.setVisibility(View.VISIBLE);
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), FirebaseDBActivity.class));
                        }
                    }
                });
    }
}
