package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        emailField = findViewById(R.id.editTextEmai);
        passwordField = findViewById(R.id.editTextPassword);


    }

    public void signIn(View v){
        System.out.println("Log in");
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        System.out.println("email: " + emailString + " password: " + passwordString);

        mAuth.signInWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("LOG IN" , "Successfully logged in the user");
                    System.out.println("success");
                    FirebaseUser user= mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    System.out.println("FAIL");
                }
            }
        });



    }






    public void signUp (View v){

//        System.out.println("sign up");
//        String emailString = emailField.getText().toString();
//        String passwordString = passwordField.getText().toString();
//        mAuth.createUserWithEmailAndPassword(emailString , passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()) {
//                    Log.d("SIGN UP" , "Successfully signed up the user");
//                    FirebaseUser user= mAuth.getCurrentUser();
//                    updateUI(user);
//                }
//
//                else {
//                    Log.w("Sign up", "createUserWithEmail:failure", task.getException());
//                    updateUI(null);
//                }
//            }
//        });

        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
//
//
    }

    public void updateUI(FirebaseUser currentuser){

        if (currentuser != null){
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }

    }
}