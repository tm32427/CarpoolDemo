package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateUserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private EditText gradYearField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private static int uidGenerator = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.linearLayout);
        userRoleSpinner = findViewById(R.id.spinner);
        setupSpinner();
        uid = "" + uidGenerator;
        uidGenerator++;
    }
    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {"Student", "Teacher", "Alumni", "Parent"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(CreateUserActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addFields() {
        commonFields();
        if(selectedRole.equals("Alumni")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduation year");
            layout.addView(gradYearField);
        }
        else if(selectedRole.equals("Student")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduation year");
            layout.addView(gradYearField);
        }
        else if(selectedRole.equals("Teacher")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Teaching Field");
            layout.addView(gradYearField);
        }
        else if(selectedRole.equals("Parent")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Number of kids in school");
            layout.addView(gradYearField);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        nameField = new EditText(this);
        nameField.setHint("Name");
        layout.addView(nameField);
        emailField = new EditText(this);
        emailField.setHint("Email");
        layout.addView(emailField);
        passwordField = new EditText(this);
        passwordField.setHint("Password");
        layout.addView(passwordField);
    }


    public void signUp(View v) {
        System.out.println("sign up");
        String nameString  = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        mAuth.createUserWithEmailAndPassword(emailString , passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("SIGN UP" , "Successfully signed up the user");
                    System.out.println("Successfully signed up the user");
                    FirebaseUser user= mAuth.getCurrentUser();
                    updateUI(user);
                }

                else {
                    Log.w("Sign up", "createUserWithEmail:failure", task.getException());
                    System.out.println("Sign up failed");
                    updateUI(null);
                }
            }
        });

        if(selectedRole.equals("Alumni")) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Alumni newUser = new Alumni(uid, nameString, emailString, gradYearInt);
            uidGenerator++;
            firestore.collection("people").document(uid).set(newUser);
        }
        else if(selectedRole.equals("Student")) {
            String gradYearStr = gradYearField.getText().toString();
            Student newUser = new Student(uid, nameString , emailString, gradYearStr);
            uidGenerator++;
            firestore.collection("people").document().set(newUser);
        }
        else if(selectedRole.equals("Teacher")) {

            String teachingField = gradYearField.getText().toString();
            Teacher newUser = new Teacher(uid, nameString,emailString, teachingField);
            uidGenerator++;
            firestore.collection("people").document(uid).set(newUser);
        }
        else if (selectedRole.equals("Parent")) {
            int kidsInSchool = Integer.parseInt(gradYearField.getText().toString());
            Parent newUser = new Parent(uid,nameString , emailString , kidsInSchool);
            uidGenerator++;
            firestore.collection("people").document(uid).set(newUser);

        }

    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
    }
}

