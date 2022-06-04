package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Segway extends Vehicle{

    private int range;
    private int weightCapacity;

    public Segway(){
    }

    public Segway(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int range, int weightCapacity) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
        this.range = range;
        this.weightCapacity = weightCapacity;
    }

    public Segway(String vehicleID, String model, int capacity, int weightCapacity, String owner, double basePrice){
        super(vehicleID, model, capacity, basePrice, owner);
        this.weightCapacity = weightCapacity;
        this.setVehicleType("Segway");
        ArrayList<String> ridersUID = new ArrayList<>();
        this.setRidersUIDs(ridersUID);
        range = 25;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString() {
        return "Segway{" +
                "range=" + range +
                ", weightCapacity=" + weightCapacity +
                '}';
    }
}
