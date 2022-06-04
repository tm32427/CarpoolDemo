package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Car extends Vehicle{
    private int range;

    public Car() {
    }

    public Car(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int range) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
        this.range = range;
    }
    public Car(String owner, String model, int capacity, double basePrice, String vehicleID){
        super(owner, model,capacity,basePrice,vehicleID);

        range = 25;
        this.setVehicleType("Car");
        ArrayList<String> ridersUID = new ArrayList<>();
        this.setRidersUIDs(ridersUID);
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Car{" +
                "range=" + range +
                '}';
    }
}
