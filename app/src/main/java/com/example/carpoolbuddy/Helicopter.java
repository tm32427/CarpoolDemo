package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Helicopter extends Vehicle{
    private int maxAltitude;
    private int maxAirSpeed;

    public Helicopter(){

    }

    public Helicopter(String vehicleID, String model, int capacity, double basePrice, String owner, int maxAltitude) {
        super(vehicleID, model, capacity,basePrice,owner);
        this.maxAltitude = maxAltitude;
        this.setVehicleType("Helicopter");

    }

    public Helicopter(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int maxAltitude, int maxAirSpeed) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
        this.maxAltitude = maxAltitude;
        this.maxAirSpeed = maxAirSpeed;
    }


    public int getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public int getMaxAirSpeed() {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(int maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "maxAltitude=" + maxAltitude +
                ", maxAirSpeed=" + maxAirSpeed +
                '}';
    }
}
