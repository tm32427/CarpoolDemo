package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Teacher extends User{

    private String inSchoolTitle;
    private String field;

    public Teacher(){
    }


    public Teacher(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String inSchoolTitle, String field) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.inSchoolTitle = inSchoolTitle;
    }

    public Teacher(String uid, String name, String email, String field){
        super(uid, name,email);
        this.field = field;

        this.inSchoolTitle = "Teacher";
        this.setUserType("Teacher");
        this.setPriceMultiplier(1.5);
        ArrayList<String> ownedVehicles = new ArrayList<>();
        this.setOwnedVehicles(ownedVehicles);
    }

    public String getInSchoolTitle() {
        return inSchoolTitle;
    }

    public void setInSchoolTitle(String inSchoolTitle) {
        this.inSchoolTitle = inSchoolTitle;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "inSchoolTitle='" + inSchoolTitle + '\'' +
                '}';
    }
}
