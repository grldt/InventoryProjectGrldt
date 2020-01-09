package com.example.dbprojectgrldt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseDBActivity extends AppCompatActivity {

    private Button btCreateDB;
    private Button btViewDB;
    private FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        firebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public  void  onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user==null){
                    Intent intent = new Intent(FirebaseDBActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        btCreateDB = (Button) findViewById(R.id.bt_createdata);
        btViewDB = (Button) findViewById(R.id.bt_viewdata);

        btCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseDBCreateActivity.getActIntent(FirebaseDBActivity.this));
            }
        });

        btViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseDBReadActivity.getActIntent(FirebaseDBActivity.this));
            }
        });
    }

}
