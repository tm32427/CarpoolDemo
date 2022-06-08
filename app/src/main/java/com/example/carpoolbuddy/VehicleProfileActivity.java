package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VehicleProfileActivity extends AppCompatActivity implements View.OnClickListener {

//    private ArrayList<Vehicle> vehiclesList;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private Vehicle selectedVehicle;
    private TextView carMaxCapacityDataTextView;
    private TextView carRemainingCapacityDataTextView;
    private TextView bookedUIDs;
    private TextView carOwnerTextView;
    private TextView vehicleTypeTextView;
    private Button buttonReserveRide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        Bundle extras = getIntent().getExtras();

    //    String currId = extras.getString("id");
      //  System.out.println(extras.getString("id"));

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        // check to see if there are any extras attached
        if(getIntent().hasExtra("selected_vehicle")) {
            System.out.println("HAS EXTRA");
            // retrieve the parcel and type case it to a Vehicle object
            selectedVehicle = (Vehicle) getIntent().getParcelableExtra("selected_vehicle");

            // retrieve the data for the vehicle
            carMaxCapacityDataTextView = findViewById(R.id.MaxCapacityDataTextView);
            carRemainingCapacityDataTextView = findViewById(R.id.RemainingCapacityDataTextView);
            bookedUIDs = findViewById(R.id.BookedUIDsDataTextView);
            carOwnerTextView = findViewById(R.id.OwnerTextView);
            vehicleTypeTextView = findViewById(R.id.VehicleTypeTextView);

            // update the TextViews with the vehicle information
            carMaxCapacityDataTextView.setText(String.valueOf(selectedVehicle.getCapacity()));
            carRemainingCapacityDataTextView.setText(String.valueOf(selectedVehicle.getRemainingCapacity()));
            bookedUIDs.setText(selectedVehicle.getRidersUIDs().toString());
            carOwnerTextView.setText(selectedVehicle.getOwner());
            vehicleTypeTextView.setText(selectedVehicle.getVehicleType());
        }

        // find the button and attach a listener
        buttonReserveRide = findViewById(R.id.buttonReserveRide);
        buttonReserveRide.setOnClickListener(this);
    }

    public void bookRide() {
        System.out.println("BOOK RIDE");
        //close vehicle if user took last seat available
        if(selectedVehicle.getRemainingCapacity() == 1) {
            firestore.collection("vehicles").document(selectedVehicle.getVehicleID())
                    .update("open", false);
        }

        // update capacity
        firestore.collection("vehicles").document(selectedVehicle.getVehicleID())
                .update("remainingCapacity", selectedVehicle.getRemainingCapacity() - 1);

        // add user's uid to the list of reservedUids
        selectedVehicle.addReservedUid(mAuth.getUid());
        firestore.collection("vehicles").document(selectedVehicle.getVehicleID())
                .update("reservedUids", selectedVehicle.getRidersUIDs())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // go back to VehiclesInfoActivity
                        Intent intent = new Intent(getApplicationContext(), VehiclesInfoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        // right here
        System.out.println("RIDERS UIDS" + selectedVehicle.getRidersUIDs());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == buttonReserveRide.getId()) {
            bookRide();
        }
        /*
        else if(i == buttonCancelRide.getID()) {
            cancelRide();
        }
        */
    }
}



