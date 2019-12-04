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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private TextView registerTxt;
    private EditText loginEmail;
    private EditText loginPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);

        registerTxt = findViewById(R.id.gotoRegister);

        loginEmail = findViewById(R.id.emailLogin);
        loginPass = findViewById(R.id.passLogin);

        loginBtn.setOnClickListener(this);
        registerTxt.setOnClickListener(this);
    }

    private void loginUser(){
        String email = loginEmail.getText().toString().trim();
        String pass = loginPass.getText().toString().trim();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(email)){
        //email is empty
            Toast.makeText(this, "Email cannot be empty!", Toast.LENGTH_SHORT).show();
            loginBtn.setVisibility(View.VISIBLE);
            return;
        }
        if(TextUtils.isEmpty(pass)){
        //pass is empty
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
            loginBtn.setVisibility(View.VISIBLE);
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), FirebaseDBActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn){
            loginBtn.setVisibility(View.INVISIBLE);
            loginUser();
        }
        if(v == registerTxt){
            startActivity(new Intent(this, Register.class));
        }
    }

    private static long back_pressed;

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) System.exit(0); //super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
