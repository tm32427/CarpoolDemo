package com.example.carpoolbuddy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Vehicle implements Parcelable {
    private String owner;
    private String model;
    private int capacity;
    private int remainingCapacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;
    private boolean open;
    private String vehicleType;
    private double basePrice;

    public Vehicle(){
    }

    public Vehicle(String vehicleID, String model, int capacity, double basePrice, String owner){
        this.vehicleID = vehicleID;
        this.model = model;
        this.capacity = capacity;
        this.basePrice = basePrice;
        this.owner = owner;

        open = true;
        vehicleType = "";
        ridersUIDs = new ArrayList<>();

    }

    public Vehicle(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.ridersUIDs = ridersUIDs;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
    }




    protected Vehicle(Parcel in){
        owner = in.readString();
        model = in.readString();
        capacity = in.readInt();
        vehicleID =in.readString();
        remainingCapacity = in.readInt();
        ridersUIDs = in.createStringArrayList();
        open = in.readByte()!= 0;
        vehicleType = in.readString();
        basePrice = in.readDouble();

    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }


    public int getRemainingCapacity(){
        return (capacity - ridersUIDs.size());
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public ArrayList<String> getRidersUIDs() {
        return ridersUIDs;
    }

    public void setRidersUIDs(ArrayList<String> ridersUIDs) {
        this.ridersUIDs = ridersUIDs;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void addReservedUid(String uid){
        ridersUIDs.add(uid);

    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "owner='" + owner + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vehicleID='" + vehicleID + '\'' +
                ", ridersUIDs=" + ridersUIDs +
                ", open=" + open +
                ", vehicleType='" + vehicleType + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(owner);
        dest.writeString(model);
        dest.writeInt(capacity);
        dest.writeInt(remainingCapacity);
        dest.writeString(vehicleID);
        dest.writeStringList(ridersUIDs);
        dest.writeString(vehicleType);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeDouble(basePrice);
    }
}
