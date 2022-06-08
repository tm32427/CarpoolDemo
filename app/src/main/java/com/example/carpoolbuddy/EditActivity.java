package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Vehicle vehicleinfo;
    private FirebaseFirestore firestore;


    //added for testing
    private ArrayList<Vehicle> vehiclesList;

    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        vehiclesList = new ArrayList<Vehicle>();
        firestore = FirebaseFirestore.getInstance();

        recView = findViewById(R.id.recycleView);
    }

    public void testDB(View v) {
        vehiclesList.clear();
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();
        firestore.collection("vehicles").whereEqualTo("open", true).whereEqualTo("owner" , mUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        vehiclesList.add(document.toObject(Vehicle.class));
                    }
                    getAllRidesTask.setResult(null);
                }
                else {
                    Log.d("VehiclesInfoActivity", "Error getting documents from db: ", task.getException());
                }
            }
        });
        // when all rides have been retrieved, update RecyclerView
        getAllRidesTask.getTask().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                dataReceived();
            }
        });
    }

    @Override
    public void onVehicleClick(int position) {
        vehiclesList.get(position);
        Intent intent = new Intent(this, VehicleProfileActivity.class);
        intent.putExtra("selected_vehicle", (Parcelable) vehiclesList.get(position));
        startActivity(intent);

    }

    public void dataReceived(){
        System.out.println("VEHICLE INFO: " + vehiclesList.toString());
        FISVehicleAdapter myAdapter = new FISVehicleAdapter(vehiclesList , this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(EditActivity.this));
    }



}