package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Parent extends User{
    private ArrayList<String> childrenUIDs;
    private int kidsInSchool;

    public Parent(){

    }

    public Parent(String uid ,String name, String email, int kidsInSchool ){
        super (uid,name,email);
        this.kidsInSchool = kidsInSchool;
        ArrayList<String> ownedVehicles = new ArrayList<>();
        this.setOwnedVehicles(ownedVehicles);
        this.setUserType("Parent");
        this.setPriceMultiplier(2.5);
        ArrayList<String> childrenUID = new ArrayList<>();
        childrenUIDs = childrenUID;

    }

    public Parent(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> childrenUIDs) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.childrenUIDs = childrenUIDs;
    }

    public ArrayList<String> getChildrenUIDs() {
        return childrenUIDs;
    }

    public void setChildrenUIDs(ArrayList<String> childrenUIDs) {
        this.childrenUIDs = childrenUIDs;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "childrenUIDs=" + childrenUIDs +
                '}';
    }
}
