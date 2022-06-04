package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Bicycle extends Vehicle{

    private int weight;
    private int weightCapacity;
    private String bicycleType;

    public Bicycle() {
    }

    public Bicycle(String vehicleID,String model, String owner, int capacity, double basePriceV, String bicycleType){
        super(vehicleID, model, capacity, basePriceV, owner);

        this.bicycleType = bicycleType;
        weight = 10;
        weightCapacity = 100;

        this.setVehicleType("Bicycle");




    }

    public Bicycle(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int weight, int weightCapacity, String bicycleType) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
        this.weight = weight;
        this.weightCapacity = weightCapacity;
        this.bicycleType = bicycleType;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public String getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
    }


    @Override
    public String toString() {
        return "Bicycle{" +
                "weight=" + weight +
                ", weightCapacity=" + weightCapacity +
                ", bicycleType='" + bicycleType + '\'' +
                '}';
    }
}
