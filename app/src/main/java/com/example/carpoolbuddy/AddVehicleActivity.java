package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.gms.common.internal.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddVehicleActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText model;
    private EditText capacity;
    private EditText basePrice;
    private EditText extraField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String vid;
    private static int vidGenerator = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.linearLayoutV);
        userRoleSpinner = findViewById(R.id.spinnerV);
        setupSpinner();
        vid = "" + vidGenerator;
        vidGenerator++;
    }
    private void setupSpinner() {
        String[] userTypes = {"Car", "Segway", "Helicopter", "Bicycle"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddVehicleActivity.this,
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
        if(selectedRole.equals("Bicycle")) {
            extraField = new EditText(this);
            extraField.setHint("bicycle type");
            layout.addView(extraField);
        }
        else if(selectedRole.equals("Segway")) {

            extraField = new EditText(this);
            extraField.setHint("Weight capacity");
            layout.addView(extraField);
        }
        else if(selectedRole.equals("Helicopter")) {

            extraField = new EditText(this);
            extraField.setHint("Max altitude");
            layout.addView(extraField);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        model = new EditText(this);
        model.setHint("Model");
        layout.addView(model);
        capacity = new EditText(this);
        capacity.setHint("capacity");
        layout.addView(capacity);
        basePrice = new EditText(this);
        basePrice.setHint("Base Price");
        layout.addView(basePrice);
    }

    public void addVehicle(View v){
        String modelString  = model.getText().toString();
        String capacityString = capacity.getText().toString();
        String basePriceString = basePrice.getText().toString();

        DocumentReference newRideRef = firestore.collection("vehicles").document();

        String vehicleID = newRideRef.getId();

        Vehicle newVehicle = null;


        if(selectedRole.equals("Car")) {
            String owner = mAuth.getCurrentUser().getUid();
            int capacityInt = Integer.parseInt(capacity.getText().toString());
            double basePriceV = Double.parseDouble(basePrice.getText().toString());
            newVehicle = new Car(vehicleID, modelString, capacityInt, basePriceV, owner);

           // firestore.collection("vehicles").document(vehicleID).set(newVehicle);
        }

        else if(selectedRole.equals("Segway")) {

            String owner = mAuth.getCurrentUser().getUid();
            String weightCap = extraField.getText().toString();
            int weightCapacity = Integer.parseInt(weightCap);
            int capacity = Integer.parseInt(capacityString);
            double basePriceV = Double.parseDouble(basePriceString);
            newVehicle = new Segway(vehicleID, modelString , capacity, weightCapacity, owner,basePriceV);

         //   firestore.collection("vehicles").document().set(newVehicle);
        }

        else if(selectedRole.equals("Helicopter")) {

            String maxHeightString = extraField.getText().toString();
            int maxHeightInt = Integer.parseInt(maxHeightString);
            int capacity = Integer.parseInt(capacityString);
            double basePriceV = Double.parseDouble(basePriceString);
            String owner = mAuth.getCurrentUser().getUid();
            newVehicle = new Helicopter(vehicleID, modelString,capacity, basePriceV, owner, maxHeightInt);

            //firestore.collection("vehicles").document(vehicleID).set(newVehicle);
        }
        else if (selectedRole.equals("Bicycle")) {
            String bikeType = extraField.getText().toString();
            int capacity = Integer.parseInt(capacityString);
            double basePriceV = Double.parseDouble(basePriceString);
            String owner = mAuth.getCurrentUser().getUid();
            newVehicle = new Bicycle(vehicleID,modelString , owner , capacity, basePriceV, bikeType);

           // firestore.collection("vehicles").document(vehicleID).set(newVehicle);
        }
        newRideRef.set(newVehicle);
    }

    public void seeVehiclesInfo(View v){
        Intent intent = new Intent(this,VehiclesInfoActivity.class);
        startActivity(intent);
    }


}